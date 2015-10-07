package client

import java.net.URI

import akka.io.IO
import spray.can.server.UHttp
import spray.can.websocket.frame.Frame
import spray.can.{Http, websocket}
import spray.http.{HttpHeaders, HttpMethods, HttpRequest}


abstract class WebSocketClient extends websocket.WebSocketClientWorker {
  import context._

  val wsUrl: String
  val (host, port, ssl, path) = parseWSURL(wsUrl)
  IO(UHttp) ! Http.Connect(host, port, ssl)

  val uuid = java.util.UUID.randomUUID.toString
  val headers = List(
    HttpHeaders.Host(host, port),
    HttpHeaders.Connection("Upgrade"),
    HttpHeaders.RawHeader("Upgrade", "websocket"),
    HttpHeaders.RawHeader("Sec-WebSocket-Version", "13"),
    HttpHeaders.RawHeader("Sec-WebSocket-Key", uuid),
    HttpHeaders.RawHeader("Sec-WebSocket-Extensions", "permessage-deflate"))


  def upgradeRequest = HttpRequest(HttpMethods.GET, path, headers)

  def parseWSURL(url: String): (String, Int, Boolean, String) = {
    val uri = new URI(url)
    (uri.getHost, uri.getPort, uri.getScheme == "wss", uri.getPath)
  }

  def businessLogic: Receive = {
    case frame: Frame => onMessage(frame)
    case _: Http.ConnectionClosed => {
      onClose()
      context.stop(self)
    }
    case x => {
      /**
       * @todo have WebSocketClientWorker fixed...
       * Should be case _:UpgradedToWebSocket but UpgradedToWebSocket is a case object.
       *     websocket.UpgradedToWebSocket is not accessible from here.
       *
       * WebSocketClientWorker should have a better notification in  handshaking
       */
      onOpen()
    }
  }

  /**
   * Let user implement standard WebSocket Api.
   * Provide with a default send method.
   */
  def send(frame: Frame): Unit = connection ! frame

  def onMessage(frame: Frame): Unit

  def onClose(): Unit

  def onOpen(): Unit

}
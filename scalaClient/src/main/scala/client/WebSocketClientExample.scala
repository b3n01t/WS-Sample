package client

import akka.actor.ActorSystem
import akka.actor.Props
import spray.can.websocket.frame.Frame

/**
 * Minimal implementation of WebSocketClient
 *
 */
class MyWsClientActor(val wsUrl: String) extends WebSocketClient {

  def onOpen(): Unit = {}

  def onMessage(frame: Frame) {
    println("Payload " + frame.payload.utf8String)
    send(frame)
  }

  def onClose(): Unit = {
    println("Closed...")
  }
}

object WebSocketClientExample extends App with MySslConfiguration {
  implicit val system = ActorSystem("websocket-client-example")

  val wsUrl = "ws://localhost:9001/"
  val wsActorClient = system.actorOf(Props(classOf[MyWsClientActor],wsUrl))

  scala.io.StdIn.readLine("Hit ENTER to exit ...\n")
  system.shutdown()
  system.awaitTermination()
}

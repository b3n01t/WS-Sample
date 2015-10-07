#WebSockets with NodeJs and Scala

Sample implementation of websockets using a NodeJs server and a Scala client.

Scala client uses [spray-websocket](https://github.com/wandoulabs/spray-websocket) and provides a ``WebSocketClient`` abstract class extending  ``websocket.WebSocketClientWorker``. ``WebSocketClient`` simplifies ``websocket.WebSocketClientWorker``'s API

##NodeJs Server
- Depends on [ws module](https://www.npmjs.com/package/ws])
- Main file: ``nodeServer/server.js``
- Listens on: ``localhost:9001``
- Install: ``npm install``
- Run: ``npm start``

##Scala Client
- Depends on [akka Actors](http://doc.akka.io/docs/akka/snapshot/scala/actors.html) and [spray-websocket](https://github.com/wandoulabs/spray-websocket)
- Main file: ``scalaClient/src/main/scala/client/WebSocketClientExample.scala``
- Connects to:``localhost:9001`` 
- Compile: ``sbt compile``
- Run: ``sbt run``

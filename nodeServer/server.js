var WebSocketServer = require("ws").Server;
var http = require("http");
var port = process.env.PORT || 9001;

var server = http.createServer();
server.listen(port);

console.log("http server listening on %d", port);

var userId;
var wss = new WebSocketServer({
    server: server
});
wss.on("connection", function(ws) {

    console.info("websocket connection open");

    var timestamp = new Date().getTime();
    userId = timestamp;

    // setInterval(function() {
    ws.send(JSON.stringify({
        msgType: "onOpenConnection",
        msg: {
            connectionId: timestamp
        }
    }));
    // }, 1000);



    ws.on("message", function(data, flags) {
        console.log("websocket received a message: ",data);
        var clientMsg = data;

        ws.send(JSON.stringify({
            msg: {
                connectionId: userId
            }
        }));


    });

    ws.on("close", function() {
        console.log("websocket connection close");
    });
});
console.log("websocket server created");
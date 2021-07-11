INSTRUCTIONS TO RUN THE APPLICATION
===================================

This application has both server and client code.

HOW TO START SERVER
===================
Open the command prompt and change directory to the project's home directory.
Once you are inside the project's home directory, run below command to start the server:

gradle runServer

This will start the server and it will ask you to press any key to begin OHLC WebSocket server.
If you press the key at this point, it will start reading the data from trades.json and will start sending the data to the subscribed clients.
You can keep it waiting till you start the clients.

HOW TO START CLIENT
===================
Open the command prompt and change directory to the project's home directory.
Once you are inside the project's home directory, run below command to start the server:

gradle runClient --args='stock_symbol'

In the argument, you pass the stock symbol for which you want the client to subscribe.
You can start as many clients (in different command prompts) as you want subscribing to any stock symbol.

Once the client is up, go to the server's terminal and press any key. Server will start sending OHLC data to the subscribed clients.
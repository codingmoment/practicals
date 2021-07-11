# Routing

In this example, we are going to make it possible to subscribe only to a subset of messages.

A binding is a relationship between an exchange and a queue. This can be simply read as: the queue is interested in messages from this exchange.

Bindings can take an extra **routingKey** parameter.

The meaning of a binding key depends on the exchange type. The fanout exchanges, which we used previously, simply ignored its value.

## Direct Exchange

Our previous tutorial _rabbitmq-pub-sub-fanout-exchange_ broadcasts all messages to all consumers. We want to extend that to allow filtering messages based on their severity. For example we may want a program which writes log messages to the disk to only receive critical errors, and not waste disk space on warning or info log messages.

We were using a fanout exchange, which doesn't give us much flexibility - it's only capable of mindless broadcasting.

We will use a **direct exchange** instead. The routing algorithm behind a direct exchange is simple - __message goes to the queues whose binding key exactly matches the routing key of the message__.

In this application, we will have one Direct Exchange and two Queues - Queue1 and Queue2.

Queue1 will be bound to exchange with routing key of "level1", "level2" and "level3".

Queue2 will be bound to exchange with routing key of "level3", "level4" and "level5".

Messages sent to the exchange with routing key of "level1" or "level2" will go to queue Queue1.

Messages sent to the exchange with routing key of "level4" or "level5" will go to queue Queue2.

Messages sent to the exchange with routing key of "level3" will go to both the queues Queue1 and Queue2.

Messages sent to the exchange with any other routing key will be discarded.
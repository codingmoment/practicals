# Publish Subscribe Pattern

In this example, we'll deliver the messages to multiple consumers. This pattern is known as "publish/subscribe"

Published messages are going to be broadcast to all the receivers.

We will be using **fanout exchange** type. A fanout exchange just broadcasts all the messages it receives to all the queues it knows.

Giving a queue a name is important when you want to share the queue between producers and consumers. 

But that's not the case for this example. We want to hear about all messages, not just a subset of them. We're also interested only in currently flowing messages not in the old ones. To solve that we need two things.

- Firstly, whenever we connect to Rabbit we need a fresh, empty queue. To do this we could create a queue with a random name, or, even better - let the server choose a random queue name for us.
- Secondly, once we disconnect the consumer the queue should be automatically deleted.

We create a **non-durable, exclusive, auto-delete queue with a auto-generated name**.

After creating a fanout exchange and a queue, we need to tell the exchange to send messages to our queue. That relationship between exchange and a queue is called a **binding**.

The messages will be lost if no queue is bound to the exchange yet, but that's okay for us; if no consumer is listening yet we can safely discard the message.

The interpretation of the result is straightforward: data from exchange logs goes to two queues with server-assigned names.
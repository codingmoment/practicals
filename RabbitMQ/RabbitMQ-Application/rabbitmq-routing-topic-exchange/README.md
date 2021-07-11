# Routing with Topic Exchange

Using a Direct Exchange has limitations - it cannot do routing based on multiple criteria.

For example, we might want to subscribe to the messages not only based on "level" but also based on "source" which emitted the log.

## Topic Exchange

Messages sent to a topic exchange cannot have an arbitrary routing_key.

Routing key must be a list of words, delimited by dots.

The words can be anything, but usually they specify some features connected to the message. There can be as many words in the routing key as you like, up to the limit of 255 bytes.

The binding key must also be in the same form. The logic behind the topic exchange is similar to a direct one - a message sent with a particular routing key will be delivered to all the queues that are bound with a matching binding key. However there are two important special cases for binding keys:

- * (star) can substitute for exactly one word.
- # (hash) can substitute for zero or more words.

In this example, we are going to send messages which describe source and level of the messages. We will use the routing key format as below:

<source>.<level>

We will have two queues Queue1, Queue2 and Queue3

Queue1 will be listening for messages coming from 'source1' and all levels.

Queue2 will be listening for messages coming from all sources with level "level3".

Queue3 will be listening for messages coming from all sources and all levels.

## Power of Topic Exchange

Topic exchange is powerful and can behave like other exchanges.

When a queue is bound with "#" (hash) binding key - it will receive all the messages, regardless of the routing key - like in fanout exchange.

When special characters, "*" (star) and "#" (hash), aren't used in bindings, the topic exchange will behave just like a direct one.

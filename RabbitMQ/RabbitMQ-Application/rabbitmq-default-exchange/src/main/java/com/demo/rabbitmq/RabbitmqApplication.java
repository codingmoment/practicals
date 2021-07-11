package com.demo.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @formatter:off
 * Spring AMQP's RabbitTemplate provides everything you need to send and receive messages.
 * 
 * However, you need to:
 * 1. Declare the Queue
 * 2. Declare the Topic Exchange
 * 3. Bind Queue to Topic Exchange
 * 4. Configure a message listener container
 * 5. Register the receiver with the message listener container
 * 6. Use RabbitTemplate to send the messages
 * 
 * DEFAULT EXCHANGE
 * ================
 * Default exchange is a direct exchange with no name pre-declared by the broker.
 * Every queue that is created is automatically bound to it with a routing key which is the same as the queue name.
 * Therefore a message published to the default exchange with the routing key "ABC" will be routed to the queue "ABC".
 * 
 * Note: To test multiple consumers, run same application multiple times with different ports.
 * 
 * @author N.Wani
 * @formatter:on
 */
@SpringBootApplication
public class RabbitmqApplication {

  static final String QUEUE_NAME = "queue-nilesh";

  /**
   * This method creates an AMQP queue.
   */
  @Bean
  public Queue queue() {
    return new Queue(QUEUE_NAME, false);
  }

  /**
   * Configure the message listener
   */
  @Bean
  public MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
    return new MessageListenerAdapter(messageReceiver, "receiveMessage");
  }

  /**
   * Configures the message listener container. Sets connection factory, queue name and registers listener.
   */
  @Bean
  public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(QUEUE_NAME);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}

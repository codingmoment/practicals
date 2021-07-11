package com.demo.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
 * DURABILITY
 * ==========
 * When RabbitMQ quits or crashes it will forget the queues and messages unless you tell it not to.
 * 
 * Two things are required to make sure that messages aren't lost: we need to mark both the queue and messages as durable.
 * 
 * Messages are persistent by default with Spring AMQP.
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
    // Making queue durable
    return new Queue(QUEUE_NAME, true);
  }

  /**
   * Configures the message listener container. Sets connection factory, queue name and registers listener.
   */
  @Bean
  public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageReceiver receiver) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(QUEUE_NAME);
    container.setMessageListener(receiver);
    // By default, acknowledge mode is AUTO.
    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    container.setPrefetchCount(1);
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}

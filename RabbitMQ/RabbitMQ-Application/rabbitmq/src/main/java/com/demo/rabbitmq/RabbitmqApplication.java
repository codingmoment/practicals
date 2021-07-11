package com.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
 * @author N.Wani
 * @formatter:on
 */
@SpringBootApplication
public class RabbitmqApplication {

  static final String TOPIC_EXCHANGE_NAME = "topic-nilesh";
  static final String QUEUE_NAME = "queue-nilesh";

  /**
   * This method creates an AMQP queue.
   */
  @Bean
  public Queue queue() {
    return new Queue(QUEUE_NAME, false);
  }

  /**
   * This method creates a topic exchange
   */
  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_EXCHANGE_NAME);
  }

  /**
   * This method binds queue with exchange.
   * 
   * We used routing key "key-nilesh.#".
   * 
   * Any message sent with a routing key that begins with "key-nilesh." are routed to the queue.
   */
  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("key-nilesh.#");
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

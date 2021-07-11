package com.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
 * @author N.Wani
 * @formatter:on
 */
@SpringBootApplication
public class RabbitmqApplication {

  static final String TOPIC_EXCHANGE_NAME = "topic-nilesh";

  /**
   * This method creates an AMQP queue.
   */
  @Bean()
  public Queue queue1() {
    // Creating a non-durable, exclusive, auto-delete queue.
    return new Queue("Queue1", false, true, true);
  }

  @Bean()
  public Queue queue2() {
    // Creating a non-durable, exclusive, auto-delete queue.
    return new Queue("Queue2", false, true, true);
  }

  @Bean()
  public Queue queue3() {
    // Creating a non-durable, exclusive, auto-delete queue.
    return new Queue("Queue3", false, true, true);
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
   */
  @Bean
  public Binding bindingQueue1(Queue queue1, TopicExchange exchange) {
    // Queue1 will be listening for messages coming from 'source1' and all levels.
    return BindingBuilder.bind(queue1).to(exchange).with("source1.#");
  }

  @Bean
  public Binding bindingQueue2(Queue queue2, TopicExchange exchange) {
    // Queue2 will be listening for messages coming from all sources with level "level3".
    return BindingBuilder.bind(queue2).to(exchange).with("#.level3");
  }

  @Bean
  public Binding bindingQueue3(Queue queue3, TopicExchange exchange) {
    // Queue3 will be listening for messages coming from all sources and all levels.
    return BindingBuilder.bind(queue3).to(exchange).with("#");
  }

  @Bean
  public MessageReceiver messageReceiverQueue1() {
    return new MessageReceiver("Queue1");
  }

  @Bean
  public MessageReceiver messageReceiverQueue2() {
    return new MessageReceiver("Queue2");
  }

  @Bean
  public MessageReceiver messageReceiverQueue3() {
    return new MessageReceiver("Queue3");
  }

  /**
   * Configures the message listener container. Sets connection factory, queue name and registers listener.
   */
  @Bean
  public SimpleMessageListenerContainer messageListenerContainer1(ConnectionFactory connectionFactory, MessageReceiver messageReceiverQueue1, Queue queue1) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue1);
    container.setMessageListener(messageReceiverQueue1);
    return container;
  }

  @Bean
  public SimpleMessageListenerContainer messageListenerContainer2(ConnectionFactory connectionFactory, MessageReceiver messageReceiverQueue2, Queue queue2) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue2);
    container.setMessageListener(messageReceiverQueue2);
    return container;
  }

  @Bean
  public SimpleMessageListenerContainer messageListenerContainer3(ConnectionFactory connectionFactory, MessageReceiver messageReceiverQueue3, Queue queue3) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue3);
    container.setMessageListener(messageReceiverQueue3);
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}

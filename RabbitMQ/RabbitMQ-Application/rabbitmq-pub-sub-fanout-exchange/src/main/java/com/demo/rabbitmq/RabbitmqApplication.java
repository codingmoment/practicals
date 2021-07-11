package com.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
 * @author N.Wani
 * @formatter:on
 */
@SpringBootApplication
public class RabbitmqApplication {

  static final String FANOUT_EXCHANGE_NAME = "fanout-nilesh";

  /**
   * This method creates an AMQP queue.
   */
  @Bean
  public Queue queue() {
    // Creating a non-durable, exclusive, auto-delete queue with a auto-generated name.
    return new Queue("", false, true, true);
  }

  /**
   * This method creates a topic exchange
   */
  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE_NAME);
  }

  /**
   * This method binds queue with exchange.
   */
  @Bean
  public Binding binding(Queue queue, FanoutExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange);
  }

  /**
   * Configures the message listener container. Sets connection factory, queue name and registers listener.
   */
  @Bean
  public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageReceiver receiver, Queue queue) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueues(queue);
    container.setMessageListener(receiver);
    return container;
  }

  public static void main(String[] args) {
    SpringApplication.run(RabbitmqApplication.class, args);
  }

}

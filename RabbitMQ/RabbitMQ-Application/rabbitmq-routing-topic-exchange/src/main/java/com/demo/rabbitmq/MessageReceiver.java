package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @formatter:off
 * Simple message listener
 * 
 * @author N.Wani
 * @formatter:on
 */
public class MessageReceiver implements MessageListener {

  private String queueName;

  public MessageReceiver(String queueName) {
    super();
    this.queueName = queueName;
  }

  @Override
  public void onMessage(Message message) {
    String receivedMessage = new String(message.getBody());
    System.out.printf("Received message \"%s\" on queue \"%s\"\n", receivedMessage, queueName);
  }
}

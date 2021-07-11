package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @formatter:off
 * Simple message listener
 * 
 * @author N.Wani
 * @formatter:on
 */
@Component
public class MessageReceiver implements MessageListener {

  @Override
  public void onMessage(Message message) {
    String receivedMessage = new String(message.getBody());

    System.out.println("Received <" + receivedMessage + ">");
    System.out.println("Processing <" + receivedMessage + ">");
    try {
      Thread.sleep(5000);
      System.out.println("Processed <" + receivedMessage + ">");
    }
    catch (Exception e) {
    }
  }
}

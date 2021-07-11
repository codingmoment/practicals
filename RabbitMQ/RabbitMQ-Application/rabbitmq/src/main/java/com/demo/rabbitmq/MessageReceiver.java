package com.demo.rabbitmq;

import org.springframework.stereotype.Component;

/**
 * POJO that defines a method for receiving messages.
 * 
 * @author N.Wani
 */
@Component
public class MessageReceiver {
  /**
   * The method that receives the messages.
   * 
   * You can name it anything, because we will be registering this method.
   * 
   * @param message The received message.
   */
  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
  }
}

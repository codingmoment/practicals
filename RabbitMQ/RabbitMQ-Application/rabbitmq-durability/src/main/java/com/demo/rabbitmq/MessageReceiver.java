package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

/**
 * @formatter:off
 * Receiver implements ChannelAwareMessageListener to get access to Channel required for manual ack.
 * 
 * In order to make sure a message is never lost, RabbitMQ supports message acknowledgments.
 * An acknowledgement is sent back by the consumer to tell RabbitMQ that a particular message has been received, 
 * processed and that RabbitMQ is free to delete it.
 * 
 * If a consumer dies (its channel is closed, connection is closed, or TCP connection is lost) without sending an ack, 
 * RabbitMQ will understand that a message wasn't processed fully and will re-queue it. 
 * If there are other consumers online at the same time, it will then quickly redeliver it to another consumer. 
 * 
 * @author N.Wani
 * @formatter:on
 */
@Component
public class MessageReceiver implements ChannelAwareMessageListener {

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    String receivedMessage = new String(message.getBody());

    System.out.println("Received <" + receivedMessage + ">");
    System.out.println("Processing <" + receivedMessage + ">");
    try {
      Thread.sleep(30000);
      System.out.println("Processed <" + receivedMessage + ">");
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
    catch (Exception e) {
    }
  }
}

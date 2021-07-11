package com.demo.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageSenderController {

  private final RabbitTemplate rabbitTemplate;

  public MessageSenderController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * This endpoint sends the message to the default exchange with a routing key of "queue-nilesh" which is same as the queue name.
   */
  @PostMapping("/send/persistent")
  public void sendPersistentMessage(@RequestBody String message) {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(RabbitmqApplication.QUEUE_NAME, message);
  }

  @PostMapping("/send/non-persistent")
  public void sendNonPersistentMessage(@RequestBody String message) {
    System.out.println("Sending message...");
    MessageBuilder msg = MessageBuilder.withBody(message.getBytes());

    rabbitTemplate.convertAndSend(RabbitmqApplication.QUEUE_NAME, msg.build(), new MessagePostProcessor() {
      @Override
      public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        return message;
      }
    });
  }
}

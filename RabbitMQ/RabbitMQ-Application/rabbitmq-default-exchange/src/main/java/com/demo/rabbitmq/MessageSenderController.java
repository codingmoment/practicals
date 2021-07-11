package com.demo.rabbitmq;

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
  @PostMapping("/send")
  public void sendMessage(@RequestBody String message) {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(RabbitmqApplication.QUEUE_NAME, message);
  }
}

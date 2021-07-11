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
   * This endpoint sends the message to the fanout exchange without any routing key.
   */
  @PostMapping("/send")
  public void sendPersistentMessage(@RequestBody String message) {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(RabbitmqApplication.FANOUT_EXCHANGE_NAME, null, message);
  }
}

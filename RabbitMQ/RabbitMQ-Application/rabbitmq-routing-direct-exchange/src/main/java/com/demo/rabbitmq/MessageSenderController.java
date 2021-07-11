package com.demo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageSenderController {

  private final RabbitTemplate rabbitTemplate;

  public MessageSenderController(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * This endpoint sends the message to the direct exchange with routing key from the request param.
   */
  @PostMapping("/send")
  public void sendPersistentMessage(@RequestParam("level") String level, @RequestBody String message) {
    System.out.println("Sending message...");
    rabbitTemplate.convertAndSend(RabbitmqApplication.DIRECT_EXCHANGE_NAME, level, message);
  }
}

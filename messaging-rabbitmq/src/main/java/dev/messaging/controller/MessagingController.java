package dev.messaging.controller;

import dev.messaging.domain.StringMessage;
import dev.messaging.domain.User;
import dev.messaging.service.consumer.RabbitConsumer;
import dev.messaging.service.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessagingController {

    private RabbitProducer producer;
    private RabbitConsumer consumer1, consumer2;

    @Autowired
    public MessagingController(RabbitProducer producer,
                               RabbitConsumer consumer1,
                               RabbitConsumer consumer2) {
        this.producer = producer;
        this.consumer1 = consumer1;
        this.consumer2 = consumer2;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody StringMessage body)   {
        producer.sendMessage(body.message());
        return ResponseEntity.ok("Message sent to RabbitMQ.");
    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> sendMessage(@RequestBody User user)   {
        producer.sendObject(user);
        return ResponseEntity.ok("Message sent to RabbitMQ.");
    }

    @GetMapping("/receive")
    public ResponseEntity<String> receiveMessage()  {
        String message = consumer1.receiveMessage();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/receiveJson")
    public ResponseEntity<User> receiveJson()  {
        User user = consumer1.receiveObject();
        return ResponseEntity.ok(user);
    }


}

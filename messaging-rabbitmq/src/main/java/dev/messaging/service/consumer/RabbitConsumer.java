package dev.messaging.service.consumer;

import dev.messaging.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    @Value("${rabbitmq.queue.name}")
    private String qName;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQName;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConsumer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitConsumer(RabbitTemplate template)  {
        this.rabbitTemplate = template;
    }

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public String receiveMessage() {
        String message = (String) rabbitTemplate.receiveAndConvert(qName);
        LOGGER.info("Received message <- {}", message);
        return  message;
    }

    public User receiveObject()   {
        User user = (User) rabbitTemplate.receiveAndConvert(jsonQName);
        LOGGER.info("Received user object <- {}", user);
        return user;
    }
}

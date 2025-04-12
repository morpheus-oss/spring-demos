package dev.messaging.service.producer;

import dev.messaging.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate template)  {
        this.rabbitTemplate = template;
    }

    public void sendMessage(String message)   {
        LOGGER.info(String.format("Sending message: %s", message));
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    public void sendObject(User user)   {
        LOGGER.info(String.format("Sending user object: %s", user));
        rabbitTemplate.convertAndSend(exchangeName, jsonRoutingKey, user);
    }

}

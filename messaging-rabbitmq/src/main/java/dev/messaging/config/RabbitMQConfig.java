package dev.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String qName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQName;

    @Value("${rabbitmq.json.routing.key}")
    private String jsonRoutingKey;

    // Beans for RabbitMQ

    @Bean
    public TopicExchange getExchange()  {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue textQueue()  {
        return new Queue(qName);
    }

    @Bean
    public Binding textBinding()  {
        return BindingBuilder
                .bind(textQueue())
                .to(getExchange())
                .with(routingKey);
    }

    @Bean
    public Queue jsonQueue()    {
        return new Queue(jsonQName);
    }

    @Bean
    public Binding jsonBinding()    {
        return BindingBuilder
                .bind(jsonQueue())
                .to(getExchange())
                .with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter jsonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate addJsonSupport(ConnectionFactory connFactory)    {
        RabbitTemplate template = new RabbitTemplate(connFactory);
        template.setMessageConverter(jsonConverter());
        return template;
    }
}

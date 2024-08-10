package br.com.acmepay.config.rabbitmq;

import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.acmepay.constants.ConstantsRabbitMQ;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue CheckDocumentQueue() {
        return new Queue(ConstantsRabbitMQ.QUEUE_CHECK_DOCUMENT, true);
    }

    @Bean
    Queue SuccessDocumentQueue() {
        return new Queue(ConstantsRabbitMQ.QUEUE_SUCCESS_DOCUMENT, true);
    }

    @Bean
    Queue FailDocumentQueue() {
        return new Queue(ConstantsRabbitMQ.QUEUE_FAIL_DOCUMENT, true);
    }

    @Bean
    public SimpleMessageConverter DocumentRequestConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("br.com.acmepay.adapters.request.DocumentRequest"));
        return converter;
    }

}

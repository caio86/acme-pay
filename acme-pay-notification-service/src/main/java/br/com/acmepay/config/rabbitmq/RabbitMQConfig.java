package br.com.acmepay.config.rabbitmq;

import java.util.List;

import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public SimpleMessageConverter DocumentRequestConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("br.com.acmepay.adapters.request.DocumentRequest"));
        return converter;
    }

}

package br.com.acmepay.adapters.output.queue.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * TopicConfiguration
 */
@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic transactions() {
        return TopicBuilder.name("transactions")
                .build();
    }

}

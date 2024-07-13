package br.com.acmepay.adapters.output.queue.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * MessageProducer
 */
@Service
@AllArgsConstructor
public class MessageProducer<T> implements IMessageProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    @Override
    public void publish(String topic, T message) {
        this.kafkaTemplate.send(topic, message);
    }

}

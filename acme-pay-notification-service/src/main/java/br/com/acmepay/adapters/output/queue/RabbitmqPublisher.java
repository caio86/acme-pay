package br.com.acmepay.adapters.output.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitmqPublisher<T> implements MessageProducer<T> {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void pubish(String queue, T payload) {
        log.info("Publishing : Payload {} / queue {}", payload, queue);
        rabbitTemplate.convertAndSend(queue, payload);
        log.info("Published : Payload {} / queue {}", payload, queue);
    }
}

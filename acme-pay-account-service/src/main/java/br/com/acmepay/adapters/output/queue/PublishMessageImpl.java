package br.com.acmepay.adapters.output.queue;

import br.com.acmepay.adapters.request.DocumentRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class PublishMessageImpl implements ProducerMessage {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DocumentRequest documentRequest) {
        log.info("Publishing : Payload {} / queue {}", documentRequest, "queue_check_document");
        this.rabbitTemplate.convertAndSend("queue_check_document", documentRequest);
        log.info("Published : Payload {} / queue {}", documentRequest, "queue_check_document");
    }
}

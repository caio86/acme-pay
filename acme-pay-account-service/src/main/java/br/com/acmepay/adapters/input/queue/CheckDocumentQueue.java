package br.com.acmepay.adapters.input.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CheckDocumentQueue {

    @RabbitListener(queues = "queue_fail_document")
    public void checkDocumentFail(String message) {
        log.info("Received Fail message: {}", message);
    }

    @RabbitListener(queues = "queue_success_document")
    public void checkDocumentSuccess(String message) {
        log.info("Received Success message: {}", message);
    }
}

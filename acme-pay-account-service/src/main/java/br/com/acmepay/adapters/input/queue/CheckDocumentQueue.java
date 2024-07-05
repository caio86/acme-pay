package br.com.acmepay.adapters.input.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.ports.in.ICheckDocumentFailUseCase;
import br.com.acmepay.application.ports.in.ICheckDocumentSuccessUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CheckDocumentQueue {

    private final ICheckDocumentSuccessUseCase documentSuccessUseCase;
    private final ICheckDocumentFailUseCase documentFailUseCase;

    @RabbitListener(queues = "queue_fail_document")
    public void checkDocumentFail(DocumentRequest message) {
        log.info("Document validation failed : {}", message);
        documentFailUseCase.execute(message);
    }

    @RabbitListener(queues = "queue_success_document")
    public void checkDocumentSuccess(DocumentRequest message) {
        log.info("Document validation succeded: {}", message);
        documentSuccessUseCase.execute(message);
    }
}

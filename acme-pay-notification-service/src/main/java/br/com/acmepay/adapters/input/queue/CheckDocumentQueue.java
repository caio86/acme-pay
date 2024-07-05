package br.com.acmepay.adapters.input.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.ports.in.ICheckDocumentUseCase;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CheckDocumentQueue {

    private final ICheckDocumentUseCase checkDocumentUseCase;

    @RabbitListener(queues = "queue_check_document")
    public void CheckDocument(DocumentRequest message) {
        checkDocumentUseCase.execute(message);
    }

}

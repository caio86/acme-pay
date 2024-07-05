package br.com.acmepay.adapters.output.queue.service;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.queue.MessageProducer;
import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.ports.out.ICheckDocumentSucess;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CheckDocumentSuccessService implements ICheckDocumentSucess {

    private final MessageProducer<DocumentRequest> messageProducer;

    @Override
    public void execute(DocumentRequest message) {
        messageProducer.pubish("queue_success_document", message);
        log.info("Publish successfully to successfull queue with payload {}", message);
    }

}

package br.com.acmepay.adapters.output.queue.service;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.queue.MessageProducer;
import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.ports.out.ICheckDocumentFail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CheckDocumentFailService implements ICheckDocumentFail {

    private final MessageProducer<DocumentRequest> messageProducer;

    @Override
    public void execute(DocumentRequest message) {
        messageProducer.pubish("queue_fail_document", message);
        log.info("Publish successfully to failed queue with payload {}", message);
    }

}

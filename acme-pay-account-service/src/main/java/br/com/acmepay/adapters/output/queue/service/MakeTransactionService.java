package br.com.acmepay.adapters.output.queue.service;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.queue.kafka.MessageProducer;
import br.com.acmepay.adapters.request.TransactionRequest;
import br.com.acmepay.application.ports.out.IMakeTransaction;
import lombok.AllArgsConstructor;

/**
 * MakeTransactionService
 */
@Service
@AllArgsConstructor
public class MakeTransactionService implements IMakeTransaction {

    private final MessageProducer<TransactionRequest> messageProducer;

    @Override
    public void execute(TransactionRequest message) {
        this.messageProducer.publish("transactions", message);
    }

}

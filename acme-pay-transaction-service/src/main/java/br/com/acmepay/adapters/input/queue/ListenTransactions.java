package br.com.acmepay.adapters.input.queue;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.request.TransactionRequest;
import br.com.acmepay.application.domain.models.TransactionDomain;
import br.com.acmepay.application.ports.in.ICreateTransactionUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ListenTransactions {

    private final ICreateTransactionUseCase createTransactionUseCase;

    @KafkaListener(topics = "transactions", groupId = "transaction-consumer-1")
    public void listen(TransactionRequest data) {
        log.info("inconming message: {}", data);

        var domain = TransactionDomain.builder()
                .source_account(data.getSourceAccount())
                .destination_account(data.getDestinationAccount())
                .data_transaction(data.getDateTransaction())
                .amount(data.getAmount())
                .build();

        createTransactionUseCase.execute(domain);
    }
}

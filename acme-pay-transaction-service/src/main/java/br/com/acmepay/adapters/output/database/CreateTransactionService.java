package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.entity.TransactionEntity;
import br.com.acmepay.adapters.output.database.repository.TransactionRepository;
import br.com.acmepay.application.domain.models.TransactionDomain;
import br.com.acmepay.application.ports.out.ICreateTransaction;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateTransactionService implements ICreateTransaction {

    private final TransactionRepository repository;

    @Override
    public void execute(TransactionDomain domain) {
        var transaction = TransactionEntity.builder()
                .source_account(domain.getSource_account())
                .destination_account(domain.getDestination_account())
                .data_transaction(domain.getData_transaction())
                .amount(domain.getAmount())
                .build();

        repository.save(transaction);
    }

}

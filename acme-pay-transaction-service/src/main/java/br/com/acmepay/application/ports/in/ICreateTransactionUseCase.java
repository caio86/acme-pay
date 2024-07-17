package br.com.acmepay.application.ports.in;

import br.com.acmepay.application.domain.models.TransactionDomain;

public interface ICreateTransactionUseCase {
    void execute(TransactionDomain transactionDomain);
}

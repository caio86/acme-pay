package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.models.TransactionDomain;

public interface ICreateTransaction {
    void execute(TransactionDomain transactionDomain);
}

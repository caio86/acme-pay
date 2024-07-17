package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.TransactionDomain;
import br.com.acmepay.application.ports.in.ICreateTransactionUseCase;
import br.com.acmepay.application.ports.out.ICreateTransaction;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateTransactionUseCase implements ICreateTransactionUseCase {

    private final ICreateTransaction createTransaction;

    @Override
    public void execute(TransactionDomain transactionDomain) {
        transactionDomain.create(createTransaction);
    }

}

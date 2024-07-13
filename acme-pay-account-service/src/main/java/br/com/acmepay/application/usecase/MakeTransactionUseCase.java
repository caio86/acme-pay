package br.com.acmepay.application.usecase;

import java.math.BigDecimal;

import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.domain.models.TransactionReturn;
import br.com.acmepay.application.ports.in.IMakeTransactionUseCase;
import br.com.acmepay.application.ports.out.IFindAccountByNumber;
import br.com.acmepay.application.ports.out.IMakeTransaction;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

/**
 * MakeTransactionUseCase
 */
@UseCase
@AllArgsConstructor
public class MakeTransactionUseCase implements IMakeTransactionUseCase {

    private final IMakeTransaction makeTransaction;
    private final IFindAccountByNumber findAccountByNumber;

    public TransactionReturn execute(
            Integer sourceAccountNumber,
            Integer destinationAccountNumber,
            BigDecimal amount) {

        var domain = AccountDomain.builder().build();

        return domain.transaction(sourceAccountNumber, destinationAccountNumber, amount, makeTransaction,
                findAccountByNumber);
    }
}

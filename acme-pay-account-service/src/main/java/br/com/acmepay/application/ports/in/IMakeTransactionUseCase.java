package br.com.acmepay.application.ports.in;

import java.math.BigDecimal;

import br.com.acmepay.application.domain.models.TransactionReturn;

/**
 * IMakeTransactionUseCase
 */
public interface IMakeTransactionUseCase {

    TransactionReturn execute(Integer sourceAccountNumber, Integer destinationAccountNumber, BigDecimal amount);
}

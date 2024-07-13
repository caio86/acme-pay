package br.com.acmepay.adapters.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * TransactionRequest
 */
@Data
@Builder
@AllArgsConstructor
public class TransactionRequest {

    private String sourceAccount;
    private String destinationAccount;
    private String dateTransaction;
    private BigDecimal amount;

}

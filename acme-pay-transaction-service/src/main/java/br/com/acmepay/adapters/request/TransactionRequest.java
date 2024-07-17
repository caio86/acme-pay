package br.com.acmepay.adapters.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * TransactionRequest
 */
@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class TransactionRequest {

    private Integer sourceAccount;
    private Integer destinationAccount;
    private LocalDateTime dateTransaction;
    private BigDecimal amount;

}

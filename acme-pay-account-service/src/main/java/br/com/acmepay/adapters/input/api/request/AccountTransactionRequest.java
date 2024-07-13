package br.com.acmepay.adapters.input.api.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactionRequest {

    private Integer sourceAccountNumber;
    private Integer destinationAccountNumber;
    private BigDecimal amount;

}

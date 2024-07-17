package br.com.acmepay.application.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.acmepay.application.ports.out.ICreateTransaction;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDomain {

    private Long id;
    private LocalDateTime data_transaction;
    private Integer source_account;
    private Integer destination_account;
    private BigDecimal amount;

    public void create(ICreateTransaction createTransaction) {
        createTransaction.execute(this);
    }
}

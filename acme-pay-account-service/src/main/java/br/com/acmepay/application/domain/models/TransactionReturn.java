package br.com.acmepay.application.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TransactionReturn
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReturn {

    private Boolean status;
    private String message;

}

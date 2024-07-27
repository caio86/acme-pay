package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetCustomerSalaryResponse {
    private final Boolean status;
    private final String errorMessage;
    private final BigDecimal response;
}

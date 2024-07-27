package br.com.acmepay.application.usecase.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardUseCaseResponse {
    private BigDecimal salary;
    private BigDecimal limit;
}

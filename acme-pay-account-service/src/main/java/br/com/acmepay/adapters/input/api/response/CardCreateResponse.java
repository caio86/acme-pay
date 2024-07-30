package br.com.acmepay.adapters.input.api.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardCreateResponse {
    private String document;
    private BigDecimal salary;
    private BigDecimal limit;

}

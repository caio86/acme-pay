package br.com.acmepay.application.ports.out;

import java.math.BigDecimal;

public interface IGetCustomerSalary {
    BigDecimal execute(String document);
}

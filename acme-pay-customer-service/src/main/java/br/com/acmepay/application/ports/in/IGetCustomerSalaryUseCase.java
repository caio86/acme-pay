package br.com.acmepay.application.ports.in;

import br.com.acmepay.application.domain.models.GetCustomerSalaryResponse;

public interface IGetCustomerSalaryUseCase {
    GetCustomerSalaryResponse execute(String document);
}

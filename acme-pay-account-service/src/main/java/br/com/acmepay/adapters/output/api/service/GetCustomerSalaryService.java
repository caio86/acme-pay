package br.com.acmepay.adapters.output.api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.api.ApiClient;
import br.com.acmepay.application.ports.out.IGetCustomerSalary;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetCustomerSalaryService implements IGetCustomerSalary {

    private final ApiClient apiClient;

    @Override
    public BigDecimal execute(String document) {
        var salary = apiClient.getSalary(document);

        return salary;
    }

}

package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;

import br.com.acmepay.application.domain.exception.CustomerNotFoundException;
import br.com.acmepay.application.ports.out.ICreateCustomer;
import br.com.acmepay.application.ports.out.IFindCustomerByDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDomain {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private BigDecimal salary;

    public String create(ICreateCustomer createCustomer) {
        return createCustomer.execute(this);
    }

    public GetCustomerSalaryResponse getCustomerSalaryUsingDocument(
            String document,
            IFindCustomerByDocument findCustomerByDocument) {

        CustomerDomain customer;

        try {
            customer = findCustomerByDocument.execute(document);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return GetCustomerSalaryResponse.builder()
                    .status(false)
                    .errorMessage("Get Customer failed with message: " + e.getMessage())
                    .response(BigDecimal.ZERO)
                    .build();
        }

        var response = GetCustomerSalaryResponse.builder()
                .status(true)
                .response(customer.getSalary())
                .errorMessage("No error")
                .build();

        return response;
    }
}

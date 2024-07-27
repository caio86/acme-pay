package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;

import br.com.acmepay.application.ports.out.ICreateCustomer;
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
}

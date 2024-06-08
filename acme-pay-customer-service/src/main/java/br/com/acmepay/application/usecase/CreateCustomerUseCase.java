package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.in.ICreateCustomerUseCase;
import br.com.acmepay.application.ports.out.ICreateCustomer;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class CreateCustomerUseCase implements ICreateCustomerUseCase {

    private final ICreateCustomer createCustomer;

    @Override
    public String execute(CustomerDomain customerDomain) {
        return createCustomer.execute(customerDomain);
    }
}

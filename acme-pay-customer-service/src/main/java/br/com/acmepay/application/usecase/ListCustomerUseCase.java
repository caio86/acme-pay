package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.in.IListCustomerUseCase;
import br.com.acmepay.application.ports.out.IListCustomer;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase
public class ListCustomerUseCase implements IListCustomerUseCase {

    private final IListCustomer listCustomer;

    @Override
    public List<CustomerDomain> execute() {
        return listCustomer.execute();
    }
}

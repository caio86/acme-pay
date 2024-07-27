package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.entity.CustomerEntity;
import br.com.acmepay.adapters.output.database.repository.CustomerRepository;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.out.ICreateCustomer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CreateCustomerService implements ICreateCustomer {

    private final CustomerRepository repository;

    @Override
    public String execute(CustomerDomain customerDomain) {
        var customer = CustomerEntity.builder()
                .name(customerDomain.getName())
                .phone(customerDomain.getPhone())
                .email(customerDomain.getEmail())
                .document(customerDomain.getDocument())
                .build();

        repository.save(customer);

        return "Customer created";
    }
}

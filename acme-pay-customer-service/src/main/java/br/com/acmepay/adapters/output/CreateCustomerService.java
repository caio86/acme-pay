package br.com.acmepay.adapters.output;

import br.com.acmepay.adapters.output.entity.CustomerEntity;
import br.com.acmepay.adapters.output.repository.CustomerRepository;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.out.ICreateCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
            .created_at(customerDomain.getCreated_at())
            .updated_at(customerDomain.getUpdated_at())
            .build();

        repository.save(customer);

        return "Customer created";
    }
}

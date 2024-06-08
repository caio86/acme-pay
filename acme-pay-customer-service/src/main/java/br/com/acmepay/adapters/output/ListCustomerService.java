package br.com.acmepay.adapters.output;

import br.com.acmepay.adapters.output.database.repository.CustomerRepository;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.out.IListCustomer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListCustomerService implements IListCustomer {

    private final CustomerRepository repository;

    @Override
    public List<CustomerDomain> execute() {
        var customers = repository.findAll();

        List<CustomerDomain> domainList = customers.stream()
            .map(item -> CustomerDomain.builder()
                .id(item.getId())
                .name(item.getName())
                .phone(item.getPhone())
                .email(item.getEmail())
                .document(item.getDocument())
                .created_at(item.getCreated_at())
                .updated_at(item.getUpdated_at())
                .build())
            .toList();

        return domainList;
    }
}

package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.repository.CustomerRepository;
import br.com.acmepay.application.domain.exception.CustomerNotFoundException;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.out.IFindCustomerByDocument;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FindCustomerByDocumentService implements IFindCustomerByDocument {

    private final CustomerRepository repository;

    @Override
    public CustomerDomain execute(String document) throws CustomerNotFoundException {
        var found = repository.findByDocument(document);

        if (found.isEmpty()) {
            throw new CustomerNotFoundException("Customer with document: " + document + " not found");
        }

        var entity = found.get();

        var domain = CustomerDomain.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .document(entity.getDocument())
                .salary(entity.getSalary())
                .build();

        return domain;
    }

}

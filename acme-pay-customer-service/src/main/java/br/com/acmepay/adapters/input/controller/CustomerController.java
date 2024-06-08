package br.com.acmepay.adapters.input.controller;

import br.com.acmepay.adapters.input.api.ICustomerResourceAPI;
import br.com.acmepay.adapters.input.api.request.CustomerCreateRequest;
import br.com.acmepay.adapters.input.api.response.CustomerCreateResponse;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.in.ICreateCustomerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class CustomerController implements ICustomerResourceAPI {

    private final ICreateCustomerUseCase createCustomerUseCase;

    @Override
    public CustomerCreateResponse create(CustomerCreateRequest request) {
        var domain = CustomerDomain.builder()
            .created_at(LocalDateTime.now())
            .updated_at(null)
            .name(request.getName())
            .email(request.getEmail())
            .phone(request.getPhone())
            .document(request.getDocument())
            .build();

        createCustomerUseCase.execute(domain);
        return CustomerCreateResponse.builder()
            .message("Customer created!")
            .build();
    }
}

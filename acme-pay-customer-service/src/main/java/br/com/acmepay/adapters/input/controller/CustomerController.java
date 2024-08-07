package br.com.acmepay.adapters.input.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.acmepay.adapters.input.api.ICustomerResourceAPI;
import br.com.acmepay.adapters.input.api.request.CustomerCreateRequest;
import br.com.acmepay.adapters.input.api.response.CustomerCreateResponse;
import br.com.acmepay.adapters.input.api.response.CustomerListResponse;
import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.ports.in.ICreateCustomerUseCase;
import br.com.acmepay.application.ports.in.IGetCustomerSalaryUseCase;
import br.com.acmepay.application.ports.in.IListCustomerUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerController implements ICustomerResourceAPI {

    private final IListCustomerUseCase listCustomerUseCase;
    private final ICreateCustomerUseCase createCustomerUseCase;
    private final IGetCustomerSalaryUseCase getCustomerSalaryUseCase;

    @Override
    public List<CustomerListResponse> list() {
        var domain = listCustomerUseCase.execute();

        var response = domain.stream()
                .map(item -> CustomerListResponse.builder()
                        .id(item.getId())
                        .name(item.getName())
                        .phone(item.getPhone())
                        .email(item.getEmail())
                        .document(item.getDocument())
                        .salary(item.getSalary())
                        .build())
                .toList();

        return response;
    }

    @Override
    public CustomerCreateResponse create(CustomerCreateRequest request) {
        var domain = CustomerDomain.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .document(request.getDocument())
                .salary(request.getSalary())
                .build();

        createCustomerUseCase.execute(domain);
        return CustomerCreateResponse.builder()
                .message("Customer created!")
                .build();
    }

    @Override
    public ResponseEntity<Object> getSalary(String document) {
        var result = getCustomerSalaryUseCase.execute(document);

        if (!result.getStatus()) {
            return new ResponseEntity<>(result.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(result.getResponse(), HttpStatus.OK);
        }
    }
}

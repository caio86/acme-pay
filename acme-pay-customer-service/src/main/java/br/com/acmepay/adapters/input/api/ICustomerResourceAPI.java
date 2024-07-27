package br.com.acmepay.adapters.input.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.acmepay.adapters.input.api.request.CustomerCreateRequest;
import br.com.acmepay.adapters.input.api.request.CustomerGetSalaryRequest;
import br.com.acmepay.adapters.input.api.response.CustomerCreateResponse;
import br.com.acmepay.adapters.input.api.response.CustomerListResponse;

@RequestMapping("/api/v1/customers")
public interface ICustomerResourceAPI {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerListResponse> list();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerCreateResponse create(@RequestBody CustomerCreateRequest request);

    @GetMapping("/salary")
    ResponseEntity<Object> getSalary(@RequestBody CustomerGetSalaryRequest request);
}

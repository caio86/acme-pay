package br.com.acmepay.adapters.input.api;

import br.com.acmepay.adapters.input.api.request.CustomerCreateRequest;
import br.com.acmepay.adapters.input.api.response.CustomerCreateResponse;
import br.com.acmepay.adapters.input.api.response.CustomerListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customers")
public interface ICustomerResourceAPI {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerListResponse> list();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerCreateResponse create(@RequestBody CustomerCreateRequest request);
}

package br.com.acmepay.adapters.input.api;

import br.com.acmepay.adapters.input.api.request.CustomerCreateRequest;
import br.com.acmepay.adapters.input.api.response.CustomerCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customers")
public interface ICustomerResourceAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerCreateResponse create(@RequestBody CustomerCreateRequest request);
}

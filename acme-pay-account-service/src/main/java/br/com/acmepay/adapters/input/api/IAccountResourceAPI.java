package br.com.acmepay.adapters.input.api;

import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/accounts")
public interface IAccountResourceAPI {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountCreateResponse create(@RequestBody AccountCreateRequest request);
}

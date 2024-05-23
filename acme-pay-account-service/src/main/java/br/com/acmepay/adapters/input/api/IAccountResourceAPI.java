package br.com.acmepay.adapters.input.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;

@RequestMapping("/api/v1/accounts")
public interface IAccountResourceAPI {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    List<AccountListResponse> list();

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountCreateResponse create(@RequestBody AccountCreateRequest request);
}

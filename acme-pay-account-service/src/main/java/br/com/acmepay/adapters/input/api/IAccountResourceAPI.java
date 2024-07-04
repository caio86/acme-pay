package br.com.acmepay.adapters.input.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;

@RequestMapping("/api/v1/accounts")
public interface IAccountResourceAPI {

    @GetMapping
    ResponseEntity<List<AccountListResponse>> list();

    @PostMapping
    ResponseEntity<AccountCreateResponse> create(@RequestBody AccountCreateRequest request);
}

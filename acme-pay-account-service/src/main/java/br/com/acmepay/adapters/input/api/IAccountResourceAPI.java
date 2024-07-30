package br.com.acmepay.adapters.input.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.request.AccountTransactionRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;
import br.com.acmepay.adapters.input.api.response.AccountTransactionResponse;
import br.com.acmepay.adapters.input.api.response.CardCreateResponse;

@RequestMapping("/api/v1/accounts")
public interface IAccountResourceAPI {

    @GetMapping("/list")
    ResponseEntity<List<AccountListResponse>> list();

    @PostMapping("/create")
    ResponseEntity<AccountCreateResponse> create(@RequestBody AccountCreateRequest request);

    @PostMapping("/transaction")
    ResponseEntity<AccountTransactionResponse> transaction(@RequestBody AccountTransactionRequest body);

    @GetMapping("/cards/{document}")
    ResponseEntity<CardCreateResponse> createCard(@PathVariable("document") String document);
}

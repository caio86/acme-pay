package br.com.acmepay.adapters.input.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import br.com.acmepay.adapters.input.api.IAccountResourceAPI;
import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import br.com.acmepay.application.ports.in.IListAccountsUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController implements IAccountResourceAPI {

    private final ICreateAccountUseCase createAccountUseCase;
    private final IListAccountsUseCase listAccountsUseCase;

    @Override
    public List<AccountListResponse> list() {
        var domain = listAccountsUseCase.execute();

        var response = domain.stream()
                .map(item -> AccountListResponse.builder()
                        .id(item.getId())
                        .number(item.getNumber())
                        .agency(item.getAgency())
                        .balance(item.getBalance())
                        .document(item.getDocument())
                        .close(item.getClose())
                        .created_at(item.getCreated_at())
                        .updated_at(item.getUpdated_at())
                        .build())
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        var domain = AccountDomain.builder()
                .created_at(LocalDateTime.now())
                .updated_at(null)
                .close(false)
                .agency(request.getAgency())
                .number(request.getNumber())
                .document(request.getDocument())
                .balance(request.getBalance())
                .build();

        createAccountUseCase.execute(domain);

        return AccountCreateResponse.builder()
                .message("account created!")
                .build();
    }
}

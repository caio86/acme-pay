package br.com.acmepay.adapters.input.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestController;

import br.com.acmepay.adapters.input.api.IAccountResourceAPI;
import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController implements IAccountResourceAPI {

    private final ICreateAccountUseCase createAccountUseCase;

    @Override
    public AccountListResponse list() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
        // return AccountListResponse.builder().build();
    }

    @Override
    public AccountCreateResponse create(AccountCreateRequest request) {
        var domain = AccountDomain.builder()
                .created_at(LocalDateTime.now())
                .updated_at(null)
                .close(false)
                .agency(request.getAgency())
                .number(request.getNumber())
                .balance(request.getBalance())
                .build();
        createAccountUseCase.execute(domain);
        return AccountCreateResponse.builder()
                .message("account created!")
                .build();
    }
}

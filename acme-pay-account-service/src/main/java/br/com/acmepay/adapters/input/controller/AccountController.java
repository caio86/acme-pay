package br.com.acmepay.adapters.input.controller;

import br.com.acmepay.adapters.input.api.IAccountResourceAPI;
import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class AccountController implements IAccountResourceAPI {

    private final ICreateAccountUseCase createAccountUseCase;

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

package br.com.acmepay.adapters.input.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.acmepay.adapters.input.api.IAccountResourceAPI;
import br.com.acmepay.adapters.input.api.request.AccountCreateRequest;
import br.com.acmepay.adapters.input.api.request.AccountTransactionRequest;
import br.com.acmepay.adapters.input.api.response.AccountCreateResponse;
import br.com.acmepay.adapters.input.api.response.AccountListResponse;
import br.com.acmepay.adapters.input.api.response.AccountTransactionResponse;
import br.com.acmepay.adapters.input.api.response.CardCreateResponse;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import br.com.acmepay.application.ports.in.ICreateCardUseCase;
import br.com.acmepay.application.ports.in.IListAccountsUseCase;
import br.com.acmepay.application.ports.in.IMakeTransactionUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AccountController implements IAccountResourceAPI {

    private final ICreateAccountUseCase createAccountUseCase;
    private final IListAccountsUseCase listAccountsUseCase;
    private final IMakeTransactionUseCase makeTransactionUseCase;
    private final ICreateCardUseCase createCardUseCase;

    @Override
    public ResponseEntity<List<AccountListResponse>> list() {
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

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountCreateResponse> create(AccountCreateRequest request) {
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

        var response = AccountCreateResponse.builder()
                .message("account creation in progress")
                .build();

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<AccountTransactionResponse> transaction(AccountTransactionRequest body) {

        var res = makeTransactionUseCase.execute(
                body.getSourceAccountNumber(),
                body.getDestinationAccountNumber(),
                body.getAmount());

        if (!res.getStatus()) {
            var response = AccountTransactionResponse.builder()
                    .message(res.getMessage())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        var response = AccountTransactionResponse.builder()
                .message(res.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CardCreateResponse> createCard(String document) {
        var result = createCardUseCase.execute(document);

        var response = CardCreateResponse.builder()
                .document(document)
                .salary(result.getSalary())
                .limit(result.getLimit())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

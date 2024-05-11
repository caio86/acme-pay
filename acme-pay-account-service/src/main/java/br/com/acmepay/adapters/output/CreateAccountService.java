package br.com.acmepay.adapters.output;

import br.com.acmepay.adapters.output.entity.AccountEntity;
import br.com.acmepay.adapters.output.repository.AccountRepository;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.out.ICreateAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CreateAccountService implements ICreateAccount {

    private final AccountRepository repository;

    @Override
    public void execute(AccountDomain accountDomain) {
        var account = AccountEntity.builder()
            .agency(accountDomain.getAgency())
            .number(accountDomain.getNumber())
            .balance(accountDomain.getBalance())
            .close(accountDomain.getClose())
            .created_at(accountDomain.getCreated_at())
            .updated_at(accountDomain.getUpdated_at())
            .build();

        repository.save(account);
    }
}

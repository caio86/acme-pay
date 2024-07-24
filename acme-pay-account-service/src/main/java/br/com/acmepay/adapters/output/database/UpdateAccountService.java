package br.com.acmepay.adapters.output.database;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.repository.AccountRepository;
import br.com.acmepay.application.domain.exception.NonExistentAccountException;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.out.IUpdateAccount;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateAccountService implements IUpdateAccount {

    private final AccountRepository repository;

    @Override
    public String execute(AccountDomain domain) throws NonExistentAccountException {
        var result = repository.findById(domain.getId());

        if (result.isEmpty()) {
            throw new NonExistentAccountException("Account with id: " + domain.getId().toString() + " does not exists");
        }

        var entity = result.get();

        entity.setNumber(domain.getNumber());
        entity.setAgency(domain.getAgency());
        entity.setDocument(domain.getDocument());
        entity.setBalance(domain.getBalance());
        entity.setClose(domain.getClose());
        entity.setUpdated_at(LocalDateTime.now());

        repository.save(entity);

        return "Updated account with id: " + domain.getId().toString();
    }

}

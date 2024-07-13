package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.repository.AccountRepository;
import br.com.acmepay.application.domain.exception.NonExistentAccountException;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.out.IFindAccountByNumber;
import lombok.AllArgsConstructor;

/**
 * FindAccountByNumberService
 */
@Service
@AllArgsConstructor
public class FindAccountByNumberService implements IFindAccountByNumber {

    private final AccountRepository repository;

    @Override
    public AccountDomain execute(Integer number) throws NonExistentAccountException {
        var result = repository.findByNumber(number);

        if (result.isEmpty()) {
            throw new NonExistentAccountException("Account with number: " + number + " does not exists");
        }

        var entity = result.get();

        var domain = AccountDomain.builder()
                .id(entity.getId())
                .agency(entity.getAgency())
                .number(entity.getNumber())
                .document(entity.getDocument())
                .balance(entity.getBalance())
                .close(entity.getClose())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();

        return domain;
    }

}

package br.com.acmepay.adapters.output;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.repository.AccountRepository;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.out.IListAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ListAccountService implements IListAccount {

    private final AccountRepository repository;

    @Override
    public List<AccountDomain> execute() {

        var accounts = repository.findAll();

        List<AccountDomain> domainList = accounts.stream()
                .map(item -> AccountDomain.builder()
                        .id(item.getId())
                        .number(item.getNumber())
                        .agency(item.getAgency())
                        .balance(item.getBalance())
                        .close(item.getClose())
                        .created_at(item.getCreated_at())
                        .updated_at(item.getUpdated_at())
                        .build())
                .collect(Collectors.toList());

        return domainList;
    }

}

package br.com.acmepay.application.ports.out;

import java.util.List;

import br.com.acmepay.application.domain.models.AccountDomain;

public interface IListAccount {
    List<AccountDomain> execute();
}

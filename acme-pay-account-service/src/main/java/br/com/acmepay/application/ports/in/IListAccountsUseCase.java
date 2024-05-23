package br.com.acmepay.application.ports.in;

import java.util.List;

import br.com.acmepay.application.domain.models.AccountDomain;

public interface IListAccountsUseCase {
    List<AccountDomain> execute();
}

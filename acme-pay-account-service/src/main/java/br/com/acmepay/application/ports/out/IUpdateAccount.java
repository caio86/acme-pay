package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.exception.NonExistentAccountException;
import br.com.acmepay.application.domain.models.AccountDomain;

public interface IUpdateAccount {
    String execute(AccountDomain domain) throws NonExistentAccountException;
}

package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.exception.NonExistentAccountException;
import br.com.acmepay.application.domain.models.AccountDomain;

/**
 * IFindAccountByNumber
 */
public interface IFindAccountByNumber {

    AccountDomain execute(Integer number) throws NonExistentAccountException;
}

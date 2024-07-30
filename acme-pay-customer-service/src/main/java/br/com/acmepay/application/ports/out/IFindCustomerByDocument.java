package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.exception.CustomerNotFoundException;
import br.com.acmepay.application.domain.models.CustomerDomain;

public interface IFindCustomerByDocument {
    CustomerDomain execute(String document) throws CustomerNotFoundException;
}

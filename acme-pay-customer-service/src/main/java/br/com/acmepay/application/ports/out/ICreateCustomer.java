package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.models.CustomerDomain;

public interface ICreateCustomer {
    String execute(CustomerDomain customerDomain);
}

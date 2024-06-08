package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.models.CustomerDomain;

import java.util.List;

public interface IListCustomer {
    List<CustomerDomain> execute();
}

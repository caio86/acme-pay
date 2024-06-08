package br.com.acmepay.application.ports.out;


import br.com.acmepay.adapters.request.DocumentRequest;

public interface ICheckCustomerDocument {
    void execute(DocumentRequest documentRequest);
}

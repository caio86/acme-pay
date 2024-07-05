package br.com.acmepay.application.ports.out;

import br.com.acmepay.adapters.request.DocumentRequest;

public interface ICheckDocumentFail {
    void execute(DocumentRequest message);
}

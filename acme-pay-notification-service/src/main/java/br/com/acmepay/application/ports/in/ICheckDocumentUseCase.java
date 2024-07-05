package br.com.acmepay.application.ports.in;

import br.com.acmepay.adapters.request.DocumentRequest;

public interface ICheckDocumentUseCase {
    void execute(DocumentRequest document);
}

package br.com.acmepay.application.ports.in;

import br.com.acmepay.adapters.request.DocumentRequest;

public interface ICheckDocumentSuccessUseCase {
    void execute(DocumentRequest documentRequest);
}

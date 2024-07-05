package br.com.acmepay.application.usecase;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICheckDocumentFailUseCase;
import br.com.acmepay.application.utils.UseCase;

@UseCase
public class CheckDocumentFailUseCase implements ICheckDocumentFailUseCase {

    @Override
    public void execute(DocumentRequest documentRequest) {
        var domain = AccountDomain.builder().build();
        domain.getAccountFromCache(documentRequest.getValidationID());
    }

}

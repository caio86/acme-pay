package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.NotificationDomain;
import br.com.acmepay.application.ports.in.ICheckDocumentUseCase;
import br.com.acmepay.application.ports.out.ICheckDocumentFail;
import br.com.acmepay.application.ports.out.ICheckDocumentSucess;
import br.com.acmepay.application.ports.out.IFindDocument;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CheckDocumentUseCase implements ICheckDocumentUseCase {

    private final IFindDocument findDocument;
    private final ICheckDocumentFail checkDocumentFail;
    private final ICheckDocumentSucess checkDocumentSucess;

    @Override
    public void execute(String document) {
        var domain = NotificationDomain.builder().build();
        domain.checkDocumentStatus(findDocument, document, checkDocumentSucess, checkDocumentFail);
    }

}

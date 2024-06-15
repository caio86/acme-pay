package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.NotificationDomain;
import br.com.acmepay.application.ports.in.ICreateDocumentUseCase;
import br.com.acmepay.application.ports.out.ICreateDocument;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class CreateDocumentUseCase implements ICreateDocumentUseCase {

    private final ICreateDocument createDocument;

    @Override
    public void execute(NotificationDomain request) {
        request.create(createDocument);
    }
}

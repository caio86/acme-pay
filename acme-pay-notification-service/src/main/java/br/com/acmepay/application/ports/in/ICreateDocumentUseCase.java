package br.com.acmepay.application.ports.in;

import br.com.acmepay.application.domain.models.NotificationDomain;

public interface ICreateDocumentUseCase {
    void execute(NotificationDomain request);
}

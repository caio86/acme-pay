package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.models.NotificationDomain;

public interface IFindDocument {
    NotificationDomain execute(String document);
}

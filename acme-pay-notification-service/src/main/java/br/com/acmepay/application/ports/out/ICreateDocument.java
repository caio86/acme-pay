package br.com.acmepay.application.ports.out;

import br.com.acmepay.application.domain.models.NotificationDomain;

public interface ICreateDocument {
    void execute(NotificationDomain notificationDomain);
}

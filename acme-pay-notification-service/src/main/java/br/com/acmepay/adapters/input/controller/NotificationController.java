package br.com.acmepay.adapters.input.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RestController;

import br.com.acmepay.adapters.input.api.INotificationResourceAPI;
import br.com.acmepay.adapters.input.api.request.NotificationCreateRequest;
import br.com.acmepay.adapters.input.api.response.NotificationCreateResponse;
import br.com.acmepay.application.domain.models.NotificationDomain;
import br.com.acmepay.application.ports.in.ICreateDocumentUseCase;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class NotificationController implements INotificationResourceAPI {

    private final ICreateDocumentUseCase createDocumentUseCase;

    @Override
    public NotificationCreateResponse create(NotificationCreateRequest request) {
        var domain = NotificationDomain.builder()
                .created_at(LocalDateTime.now())
                .updated_at(null)
                .document(request.getDocument())
                .status(request.getStatus())
                .build();

        createDocumentUseCase.execute(domain);

        return NotificationCreateResponse.builder()
                .message("Document created!")
                .build();
    }
}

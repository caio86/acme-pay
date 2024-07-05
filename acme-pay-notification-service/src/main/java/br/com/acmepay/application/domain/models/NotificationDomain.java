package br.com.acmepay.application.domain.models;

import java.time.LocalDateTime;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.ports.out.ICheckDocumentFail;
import br.com.acmepay.application.ports.out.ICheckDocumentSucess;
import br.com.acmepay.application.ports.out.ICreateDocument;
import br.com.acmepay.application.ports.out.IFindDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class NotificationDomain {

    public enum Statuses {
        ACTIVE,
        SUSPENDED
    }

    private Long id;
    private String document;
    private Statuses status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public void create(ICreateDocument createDocument) {
        createDocument.execute(this);
    };

    public void checkDocumentStatus(
            IFindDocument findDocument,
            DocumentRequest documentRequest,
            ICheckDocumentSucess checkDocumentSucess,
            ICheckDocumentFail checkDocumentFail) {

        var foundDocument = findDocument.execute(documentRequest.getDocument());
        if (foundDocument == null) {
            log.info("Documento não foi criado");
            checkDocumentFail.execute(documentRequest);
            return;
        }

        var status = foundDocument.getStatus();
        if (status == Statuses.ACTIVE) {
            log.info("--- Documento Ativo: {} ---", documentRequest.getDocument());
            checkDocumentSucess.execute(documentRequest);
        } else {
            log.info("--- Documento Suspenso: {} ---", documentRequest.getDocument());
            checkDocumentFail.execute(documentRequest);
        }
    };
}

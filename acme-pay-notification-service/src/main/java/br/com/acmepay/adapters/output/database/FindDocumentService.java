package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.repository.NotificationRepository;
import br.com.acmepay.application.domain.models.NotificationDomain;
import br.com.acmepay.application.ports.out.IFindDocument;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FindDocumentService implements IFindDocument {

    private final NotificationRepository repository;

    @Override
    public NotificationDomain execute(String document) {
        var result = repository.findByDocument(document);

        if (result.isEmpty()) {
            return null;
        }

        var entity = result.get();

        var domain = NotificationDomain.builder()
                .id(entity.getId())
                .document(entity.getDocument())
                .status(entity.getStatus())
                .created_at(entity.getCreated_at())
                .updated_at(entity.getUpdated_at())
                .build();

        return domain;
    }
}

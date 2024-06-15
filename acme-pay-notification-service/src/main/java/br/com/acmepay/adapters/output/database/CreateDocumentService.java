package br.com.acmepay.adapters.output.database;

import org.springframework.stereotype.Service;

import br.com.acmepay.adapters.output.database.entity.NotificationEntity;
import br.com.acmepay.adapters.output.database.repository.NotificationRepository;
import br.com.acmepay.application.domain.models.NotificationDomain;
import br.com.acmepay.application.ports.out.ICreateDocument;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreateDocumentService implements ICreateDocument {

    private final NotificationRepository repository;

    @Override
    public void execute(NotificationDomain notificationDomain) {
        var entity = NotificationEntity.builder()
                .document(notificationDomain.getDocument())
                .status(notificationDomain.getStatus())
                .created_at(notificationDomain.getCreated_at())
                .updated_at(notificationDomain.getUpdated_at())
                .build();

        repository.save(entity);
    }

}

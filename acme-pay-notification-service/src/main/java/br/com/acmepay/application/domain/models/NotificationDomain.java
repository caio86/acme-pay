package br.com.acmepay.application.domain.models;

import java.time.LocalDateTime;

import br.com.acmepay.application.ports.out.ICreateDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}

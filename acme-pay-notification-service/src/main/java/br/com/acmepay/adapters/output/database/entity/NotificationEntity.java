package br.com.acmepay.adapters.output.database.entity;

import java.time.LocalDateTime;

import br.com.acmepay.application.domain.models.NotificationDomain.Statuses;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    private Long id;
    private String document;

    @Enumerated(EnumType.STRING)
    private Statuses status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}

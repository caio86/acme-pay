package br.com.acmepay.adapters.input.api.request;

import br.com.acmepay.application.domain.models.NotificationDomain.Statuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationCreateRequest {

    private String document;
    private Statuses status;
}

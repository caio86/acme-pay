package br.com.acmepay.adapters.input.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.acmepay.adapters.input.api.request.NotificationCreateRequest;
import br.com.acmepay.adapters.input.api.response.NotificationCreateResponse;

@RequestMapping("/api/v1/notifications")
public interface INotificationResourceAPI {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    NotificationCreateResponse create(@RequestBody NotificationCreateRequest request);
}

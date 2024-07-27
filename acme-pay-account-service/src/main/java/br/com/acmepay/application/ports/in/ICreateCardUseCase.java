package br.com.acmepay.application.ports.in;

import br.com.acmepay.application.usecase.response.CreateCardUseCaseResponse;

public interface ICreateCardUseCase {
    CreateCardUseCaseResponse execute(String document);
}

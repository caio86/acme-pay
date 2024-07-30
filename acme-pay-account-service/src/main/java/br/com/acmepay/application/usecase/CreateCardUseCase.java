package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateCardUseCase;
import br.com.acmepay.application.ports.out.IGetCustomerSalary;
import br.com.acmepay.application.usecase.response.CreateCardUseCaseResponse;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateCardUseCase implements ICreateCardUseCase {

    private final IGetCustomerSalary getCustomerSalary;

    @Override
    public CreateCardUseCaseResponse execute(String document) {
        var domain = AccountDomain.builder().build();

        return domain.createCard(document, getCustomerSalary);
    }

}

package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICreateAccountUseCase;
import br.com.acmepay.application.ports.out.ICheckCustomerDocument;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class CreateAccountUseCase implements ICreateAccountUseCase {

    private final ICheckCustomerDocument checkCustomerDocument;

    @Override
    public void execute(AccountDomain domain) {
        domain.create(checkCustomerDocument);
    }
}

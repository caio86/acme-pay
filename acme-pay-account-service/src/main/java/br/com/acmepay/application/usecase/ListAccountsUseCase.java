package br.com.acmepay.application.usecase;

import java.util.List;

import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.IListAccountsUseCase;
import br.com.acmepay.application.ports.out.IListAccount;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class ListAccountsUseCase implements IListAccountsUseCase {

    private final IListAccount listAccount;

    @Override
    public List<AccountDomain> execute() {
        return listAccount.execute();
    }

}

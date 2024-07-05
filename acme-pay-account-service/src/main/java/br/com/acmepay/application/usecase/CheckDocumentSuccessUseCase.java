package br.com.acmepay.application.usecase;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.domain.models.AccountDomain;
import br.com.acmepay.application.ports.in.ICheckDocumentSuccessUseCase;
import br.com.acmepay.application.ports.out.ICreateAccount;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@AllArgsConstructor
public class CheckDocumentSuccessUseCase implements ICheckDocumentSuccessUseCase {

    private final ICreateAccount createAccount;

    @Override
    public void execute(DocumentRequest documentRequest) {
        var domain = AccountDomain.builder().build();
        var accountFromCache = domain.getAccountFromCache(documentRequest.getValidationID());
        if (accountFromCache == null) {
            log.error("Account not in cache");
            return;
        }
        createAccount.execute(accountFromCache);
    }
}

package br.com.acmepay.application.usecase;

import br.com.acmepay.application.domain.models.CustomerDomain;
import br.com.acmepay.application.domain.models.GetCustomerSalaryResponse;
import br.com.acmepay.application.ports.in.IGetCustomerSalaryUseCase;
import br.com.acmepay.application.ports.out.IFindCustomerByDocument;
import br.com.acmepay.application.utils.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GetCustomerSalaryUseCase implements IGetCustomerSalaryUseCase {

    private final IFindCustomerByDocument findCustomerByDocument;

    @Override
    public GetCustomerSalaryResponse execute(String document) {
        var domain = CustomerDomain.builder().build();

        return domain.getCustomerSalaryUsingDocument(
                document,
                findCustomerByDocument);
    }

}

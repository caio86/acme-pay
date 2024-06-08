package br.com.acmepay.application.domain.models;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
import br.com.acmepay.application.domain.exception.InvalidDocumentException;
import br.com.acmepay.application.ports.out.ICheckCustomerDocument;
import br.com.acmepay.application.ports.out.ICreateAccount;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDomain {

    private Long id;
    private Integer number;
    private Integer agency;
    private String document;
    private BigDecimal balance;
    private Boolean close;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public void create(ICreateAccount createAccount, ICheckCustomerDocument checkCustomerDocument) {

        // recuperar o documento
        var doc = DocumentRequest.builder()
                .document(this.getDocument())
                .build();

        // enviar para verificação
        checkCustomerDocument.execute(doc);

        createAccount.execute(this);
    }

    public void deposit(BigDecimal amount) {
        setBalance(this.balance.add(amount));
    }

    public void withdraw(BigDecimal amount) throws BalanceToWithdrawException {
        if (this.balance.compareTo(amount) >= 0) {
            setBalance(this.balance.subtract(amount));
        } else {
            throw new BalanceToWithdrawException("error withdraw");
        }
    }

    public void validateDocument(String document) throws InvalidDocumentException {
        if (document.length() != 11) {
            throw new InvalidDocumentException("CPF Inválido");
        }
    }

}

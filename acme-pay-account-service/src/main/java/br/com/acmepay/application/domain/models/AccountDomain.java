package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import br.com.acmepay.adapters.request.DocumentRequest;
import br.com.acmepay.adapters.request.TransactionRequest;
import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
import br.com.acmepay.application.ports.out.ICheckCustomerDocument;
import br.com.acmepay.application.ports.out.IFindAccountByNumber;
import br.com.acmepay.application.ports.out.IMakeTransaction;
import br.com.acmepay.application.ports.out.IUpdateAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDomain {

    private static final ConcurrentHashMap<String, AccountDomain> cache = new ConcurrentHashMap<>();

    private Long id;
    private Integer number;
    private Integer agency;
    private String document;
    private BigDecimal balance;
    private Boolean close;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public void create(ICheckCustomerDocument checkCustomerDocument) {
        String validationID = UUID.randomUUID().toString();
        cache.put(validationID, this);

        // recuperar o documento
        var doc = DocumentRequest.builder()
                .document(this.getDocument())
                .validationID(validationID)
                .build();

        // enviar para verificação
        checkCustomerDocument.execute(doc);
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

    public AccountDomain getAccountFromCache(String validationID) {
        return cache.remove(validationID);
    }

    public TransactionReturn transaction(
            Integer sourceAccountNumber,
            Integer destinationAccountNumber,
            BigDecimal amount,
            IMakeTransaction makeTransaction,
            IFindAccountByNumber findAccountByNumber,
            IUpdateAccount updateAccount) {

        AccountDomain sourceAccount;
        AccountDomain destinationAccount;

        try {
            sourceAccount = findAccountByNumber.execute(sourceAccountNumber);
            destinationAccount = findAccountByNumber.execute(destinationAccountNumber);

            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);

            updateAccount.execute(sourceAccount);
            updateAccount.execute(destinationAccount);
        } catch (Exception e) {
            return TransactionReturn.builder()
                    .status(false)
                    .message("Transaction error: " + e.getMessage())
                    .build();
        }

        var transaction = TransactionRequest.builder()
                .sourceAccount(sourceAccount.getNumber())
                .destinationAccount(destinationAccount.getNumber())
                .dateTransaction(LocalDateTime.now())
                .amount(amount)
                .build();

        makeTransaction.execute(transaction);

        return TransactionReturn.builder()
                .status(true)
                .message("Transação concluida")
                .build();
    };

}

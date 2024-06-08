package br.com.acmepay.application.domain.models;

import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
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

    public void create(ICreateAccount createAccount) {
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

}

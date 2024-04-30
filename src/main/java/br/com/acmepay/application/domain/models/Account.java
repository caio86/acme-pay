package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private Integer number;
    private Integer agency;
    private BigDecimal balance;
    private Boolean close;
    private String customer;
    private List<Integer> cards;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private List<String> transactions = new ArrayList<>();

    public void create(Account accountDomain) {
        this.setId(accountDomain.id);
        this.setCreated_at(LocalDateTime.now());
        this.setUpdated_at(null);
        this.setCustomer(null);
        this.setCards(new ArrayList<>());
        this.setBalance(BigDecimal.ZERO);
        this.setNumber(accountDomain.number);
        this.setAgency(accountDomain.agency);
        this.setClose(accountDomain.close);
        this.transactions.add("account created successfully at: " + this.getCreated_at());
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        this.transactions.add(LocalDateTime.now().toString() + " deposit successfully, amount: " + amount.toString());
    }

    public void withdraw(BigDecimal amount) throws BalanceToWithdrawException {
        if (checkTransfer(amount)) {
            this.balance = this.balance.subtract(amount);
            this.transactions
                    .add(LocalDateTime.now().toString() + " withdraw successfully, amount: " + amount.toString());
        } else {
            this.transactions.add(LocalDateTime.now().toString() + " Error to withdraw, amount: " + amount.toString());
            throw new BalanceToWithdrawException("error withdraw");
        }
    }

    public void transfer(Account destAccountDomain, BigDecimal amount) throws BalanceToWithdrawException {
        this.withdraw(amount);
        destAccountDomain.deposit(amount);
        this.transactions
                .add(LocalDateTime.now().toString() + " transaction successfully, amount: " + amount.toString());
    }

    public void getTransactions() {
        for (String transaction : this.transactions) {
            System.out.println(transaction);
        }
    }

    private boolean checkTransfer(BigDecimal amount) {
        return this.balance.compareTo(amount) >= 0;
    }

}

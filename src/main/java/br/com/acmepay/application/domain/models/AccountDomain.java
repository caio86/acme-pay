package br.com.acmepay.application.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.acmepay.application.domain.exception.BalanceToWithdrawException;

public class AccountDomain {

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

    public void create(AccountDomain accountDomain) {
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

    public void transfer(AccountDomain destAccountDomain, BigDecimal amount) throws BalanceToWithdrawException {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean getClose() {
        return close;
    }

    public void setClose(Boolean close) {
        this.close = close;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}

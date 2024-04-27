package br.com.acmepay.application.domain.exception;

public class CustomerWithIdNotExists extends Exception {
    public CustomerWithIdNotExists() {
        super();
    }

    public CustomerWithIdNotExists(String message) {
        super(message);
    }
}

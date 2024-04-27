package br.com.acmepay.exception;

public class CustomerWithIdNotExists extends Exception {
    public CustomerWithIdNotExists() {
        super();
    }

    public CustomerWithIdNotExists(String message) {
        super(message);
    }
}

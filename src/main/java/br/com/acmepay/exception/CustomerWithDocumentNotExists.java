package br.com.acmepay.exception;

public class CustomerWithDocumentNotExists extends Exception {
    public CustomerWithDocumentNotExists() {
        super();
    }

    public CustomerWithDocumentNotExists(String message) {
        super(message);
    }
}

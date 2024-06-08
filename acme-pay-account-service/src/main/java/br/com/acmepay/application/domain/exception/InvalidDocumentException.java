package br.com.acmepay.application.domain.exception;

public class InvalidDocumentException extends Exception {

    public InvalidDocumentException() {
        super();
    }

    public InvalidDocumentException(String message) {
        super(message);
    }
}

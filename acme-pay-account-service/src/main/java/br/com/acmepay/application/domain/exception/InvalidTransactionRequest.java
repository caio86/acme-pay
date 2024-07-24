package br.com.acmepay.application.domain.exception;

/**
 * InvalidTransactionRequest
 */
public class InvalidTransactionRequest extends Exception {

    public InvalidTransactionRequest() {
        super("Invalid Transaction Request");
    }

    public InvalidTransactionRequest(String message) {
        super(message);
    }

}

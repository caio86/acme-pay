package br.com.acmepay.application.domain.exception;

/**
 * NonExistentAccountException
 */
public class NonExistentAccountException extends Exception {

    public NonExistentAccountException() {
        super();
    }

    public NonExistentAccountException(String message) {
        super(message);
    }

}

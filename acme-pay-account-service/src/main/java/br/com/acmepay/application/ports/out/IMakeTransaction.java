package br.com.acmepay.application.ports.out;

import br.com.acmepay.adapters.request.TransactionRequest;

/**
 * IMakeTransaction
 */
public interface IMakeTransaction {

    void execute(TransactionRequest message);
}

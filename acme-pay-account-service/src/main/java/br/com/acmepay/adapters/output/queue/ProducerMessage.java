package br.com.acmepay.adapters.output.queue;

import br.com.acmepay.adapters.request.DocumentRequest;

public interface ProducerMessage {
    void publish(DocumentRequest documentRequest);
}

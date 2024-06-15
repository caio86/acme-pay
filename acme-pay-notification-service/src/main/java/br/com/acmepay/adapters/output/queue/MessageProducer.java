package br.com.acmepay.adapters.output.queue;

public interface MessageProducer {
    void pubish(String queue, String payload);
}

package br.com.acmepay.adapters.output.queue;

public interface MessageProducer<T> {
    void pubish(String queue, T payload);
}

package br.com.acmepay.adapters.output.queue.kafka;

/**
 * IMessageProducer
 */
public interface IMessageProducer<T> {

    void publish(String topic, T message);
}

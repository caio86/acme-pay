package br.com.acmepay.constants;

public class ConstantsRabbitMQ {

    String EXCHANGE_CHECK_DOCUMENT = "check_document_queue_exchange";
    String EXCHANGE_CHECK_DOCUMENT_SUCCESS = "check_document_queue_exchange_success";
    String EXCHANGE_CHECK_DOCUMENT_FAIL = "check_document_queue_exchange_fail";

    String QUEUE_CHECK_DOCUMENT = "queue_check_document";
    String QUEUE_SUCCESS_DOCUMENT = "queue_success_document";
    String QUEUE_FAIL_DOCUMENT = "queue_fail_document";

}

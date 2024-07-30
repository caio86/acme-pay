package br.com.acmepay.adapters.output.api;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customers", url = "http://localhost:8082")
public interface ApiClient {

    @GetMapping("/api/v1/customers/salary/{document}")
    BigDecimal getSalary(@PathVariable("document") String document);
}

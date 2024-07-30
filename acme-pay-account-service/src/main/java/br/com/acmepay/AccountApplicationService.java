package br.com.acmepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "br.com.acmepay.adapters.output.api")
@SpringBootApplication
public class AccountApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplicationService.class, args);
    }
}

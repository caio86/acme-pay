package br.com.acmepay.adapters.output.repository;

import br.com.acmepay.adapters.output.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}

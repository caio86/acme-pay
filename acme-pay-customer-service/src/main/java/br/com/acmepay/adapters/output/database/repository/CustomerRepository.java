package br.com.acmepay.adapters.output.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acmepay.adapters.output.database.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}

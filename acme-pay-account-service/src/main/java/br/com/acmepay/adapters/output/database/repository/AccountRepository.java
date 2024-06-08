package br.com.acmepay.adapters.output.database.repository;

import br.com.acmepay.adapters.output.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}

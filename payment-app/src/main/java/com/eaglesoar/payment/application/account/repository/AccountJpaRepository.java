package com.eaglesoar.payment.application.account.repository;

import com.eaglesoar.payment.domain.model.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long>, RevisionRepository<AccountEntity, Long, Long> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByAccountNumberAndStatus(String accountNumber, AccountStatus status);

}

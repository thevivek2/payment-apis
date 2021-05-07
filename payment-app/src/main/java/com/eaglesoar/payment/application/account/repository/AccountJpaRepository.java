package com.eaglesoar.payment.application.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long>, RevisionRepository<AccountEntity, Long, Long> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

}

package com.eaglesoar.payment.domain.repository;

import com.eaglesoar.payment.domain.model.Account;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findBy(String accountNumber);
}

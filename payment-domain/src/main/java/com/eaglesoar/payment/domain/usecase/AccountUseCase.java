package com.eaglesoar.payment.domain.usecase;

import com.eaglesoar.payment.domain.model.Account;
import com.eaglesoar.payment.domain.exception.AccountNotFoundException;
import com.eaglesoar.payment.domain.model.AccountStatus;
import com.eaglesoar.payment.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountUseCase {

    private final AccountRepository repository;

    public Account create(Account account) {
        account.setStatus(AccountStatus.OPEN);
        account.setBalance(BigDecimal.ZERO);
        return repository.save(account);
    }

    public Account update(String accountNumber, Account account) {
        return repository.findBy(accountNumber).map((saved) -> {
            saved.setStatus(account.getStatus());
            return repository.save(saved);
        }).orElseThrow(() -> AccountNotFoundException.of(accountNumber));
    }
}

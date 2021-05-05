package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.exception.DataNotFoundException;
import com.eaglesoar.paymentapplication.exception.DuplicateDataException;
import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountJpaRepository;
import com.eaglesoar.paymentapplication.repository.AccountStatus;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountJpaRepository repository;

    @Override
    public AccountEntity create(String accountNumber, String currency) {
        try {
            return repository.save(AccountEntity.of(accountNumber, BigDecimal.ZERO, currency, AccountStatus.OPEN));
        } catch (DataIntegrityViolationException e) {

            throw DuplicateDataException.of(String.format("Account Number %s already exists", accountNumber));
        }
    }

    @Override
    public AccountEntity update(String accountNumber, AccountStatus status) {
        return repository.findByAccountNumber(accountNumber).map((account) -> {
            account.setStatus(status);
            return repository.save(account);
        }).orElseThrow(() -> new DataNotFoundException(String.format("AccountNumber %s not exists", accountNumber)));
    }


}

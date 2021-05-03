package com.eaglesoar.paymentapplication.service;

import com.eaglesoar.paymentapplication.exception.DuplicateDataException;
import com.eaglesoar.paymentapplication.repository.AccountEntity;
import com.eaglesoar.paymentapplication.repository.AccountJpaRepository;
import com.eaglesoar.paymentapplication.repository.AccountStatus;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
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
        } catch (ConstraintViolationException e) {
            throw DuplicateDataException.of(String.format("Accountnumber %s already exists", accountNumber));
        }
    }
}

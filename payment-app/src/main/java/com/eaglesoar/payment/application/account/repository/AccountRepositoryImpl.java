package com.eaglesoar.payment.application.account.repository;

import com.eaglesoar.payment.application.account.mapper.AccountMapper;
import com.eaglesoar.payment.domain.model.Account;
import com.eaglesoar.payment.domain.model.AccountStatus;
import com.eaglesoar.payment.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountJpaRepository jpaRepository;
    private final AccountMapper mapper;

    @Override
    public Account save(Account account) {
        return mapper.toModel(jpaRepository.save(mapper.toEntity(account)));
    }

    @Override
    public Optional<Account> findBy(String accountNumber) {
        return jpaRepository.findByAccountNumber(accountNumber).map(mapper::toModel);
    }

    @Override
    public boolean isExists(String accountNumber) {
        return jpaRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public boolean isExists(String accountNumber, AccountStatus status) {
        return jpaRepository.existsByAccountNumberAndStatus(accountNumber, status);
    }
}

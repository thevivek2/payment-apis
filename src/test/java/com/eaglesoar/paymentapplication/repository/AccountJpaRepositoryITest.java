package com.eaglesoar.paymentapplication.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AccountJpaRepositoryITest {

    @Autowired
    private AccountJpaRepository repository;

    @Test
    void save() {
        AccountEntity save = repository.save(account("2"));
        assertThat(save.getId()).isNotNull();
    }

    @Test
    void findByAccountNumber() {
        String accountNumber = "2";
        repository.save(account(accountNumber));
        assertThat(repository.findByAccountNumber(accountNumber)
                .get().getStatus()).isEqualTo(AccountStatus.OPEN);
    }

    private static AccountEntity account(String accountNumber) {
        return AccountEntity.of(accountNumber, BigDecimal.TEN, "NRS", AccountStatus.OPEN);
    }
}
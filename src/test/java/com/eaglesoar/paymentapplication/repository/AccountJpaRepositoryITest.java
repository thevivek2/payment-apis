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
        AccountEntity save = repository.save(account());
        assertThat(save.getId()).isNotNull();
    }

    private static AccountEntity account() {
        return AccountEntity.of("2", BigDecimal.TEN, "NRS", AccountStatus.OPEN);
    }
}
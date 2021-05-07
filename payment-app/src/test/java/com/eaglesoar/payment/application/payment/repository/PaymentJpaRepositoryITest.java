package com.eaglesoar.payment.application.payment.repository;

import com.eaglesoar.payment.domain.model.PaymentStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class PaymentJpaRepositoryITest {

    @Autowired
    private PaymentJpaRepository repository;

    @Test
    void save() {
        PaymentEntity entity = new PaymentEntity();
        entity.setStatus(PaymentStatus.SAVED);
        entity.setAmount(BigDecimal.TEN);
        entity.setCurrency("NRS");
        entity.setCreditorAccountNumber("2");
        entity.setDebtorAccountNumber("3");
        PaymentEntity save = repository.save(entity);
        Assertions.assertThat(save.getId()).isNotNull();
        Assertions.assertThat(save.getStatus()).isEqualTo(PaymentStatus.SAVED);
    }

}
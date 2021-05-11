package com.eaglesoar.payment.application.payment.charge;

import com.eaglesoar.payment.domain.model.Charge;
import com.eaglesoar.payment.domain.model.Payment;
import com.eaglesoar.payment.domain.service.ChargeCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChargeCalculatorImplTest {

    private ChargeCalculator chargeCalculator = new ChargeCalculatorImpl();

    @Test
    void calculate() {
        Payment payment = Payment.builder().senderAccountNumber("2").receiverAccountNumber("3").amount(BigDecimal.TEN).build();
        List<Charge> charges = chargeCalculator.calculate(payment);
        assertThat(charges.size()).isEqualTo(1);
    }

    @Test
    void givenPaymentWithLargeAmount_whenCalculate_ShouldHaveSystemCharge() {
        Payment payment = Payment.builder().senderAccountNumber("2").receiverAccountNumber("3").amount(BigDecimal.valueOf(10000)).build();
        List<Charge> charges = chargeCalculator.calculate(payment);
        assertThat(charges.size()).isEqualTo(2);
    }
}
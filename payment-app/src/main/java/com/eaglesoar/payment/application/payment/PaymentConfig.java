package com.eaglesoar.payment.application.payment;

import com.eaglesoar.payment.domain.repository.AccountRepository;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import com.eaglesoar.payment.domain.usecase.PaymentUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class PaymentConfig {

    @Bean
    PaymentUseCase paymentUseCase(PaymentRepository repository, AccountRepository accountRepository,
                                  @Value("${payment.credit.max.transfer.amount}") BigDecimal maxTransferLimit) {
        return new PaymentUseCase(repository, accountRepository, maxTransferLimit);
    }
}

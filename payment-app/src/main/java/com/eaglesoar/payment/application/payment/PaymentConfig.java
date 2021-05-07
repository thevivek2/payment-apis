package com.eaglesoar.payment.application.payment;

import com.eaglesoar.payment.domain.usecase.PaymentUseCase;
import com.eaglesoar.payment.domain.repository.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    PaymentUseCase paymentUseCase(PaymentRepository repository) {
        return new PaymentUseCase(repository);
    }
}

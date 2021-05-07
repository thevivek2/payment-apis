package com.eaglesoar.paymentapplication.payment;

import com.eaglesoar.payment.repository.PaymentRepository;
import com.eaglesoar.payment.usecase.PaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    PaymentUseCase paymentUseCase(PaymentRepository repository) {
        return new PaymentUseCase(repository);
    }
}

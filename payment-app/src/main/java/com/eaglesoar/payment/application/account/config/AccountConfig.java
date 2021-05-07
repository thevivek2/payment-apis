package com.eaglesoar.payment.application.account.config;

import com.eaglesoar.payment.domain.repository.AccountRepository;
import com.eaglesoar.payment.domain.usecase.AccountUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Bean
    AccountUseCase useCase(AccountRepository repository) {
        return new AccountUseCase(repository);
    }
}

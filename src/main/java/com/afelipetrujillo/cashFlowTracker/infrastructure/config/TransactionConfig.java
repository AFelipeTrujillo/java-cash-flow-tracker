package com.afelipetrujillo.cashFlowTracker.infrastructure.config;

import com.afelipetrujillo.cashFlowTracker.application.usecase.RegisterTransactionUseCase;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public RegisterTransactionUseCase registerTransactionUseCase(TransactionRepository repository) {
        return new RegisterTransactionUseCase(repository);
    }
}

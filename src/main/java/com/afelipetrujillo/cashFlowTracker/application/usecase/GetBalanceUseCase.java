package com.afelipetrujillo.cashFlowTracker.application.usecase;

import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class GetBalanceUseCase {

    private final TransactionRepository repository;

    public BigDecimal execute(){
        return repository.findAll().stream().map(Transaction::getSignedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

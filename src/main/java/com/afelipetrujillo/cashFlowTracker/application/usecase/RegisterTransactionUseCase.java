package com.afelipetrujillo.cashFlowTracker.application.usecase;

import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterTransactionUseCase {

    private TransactionRepository repository;

    public Transaction execute(Transaction transaction) {
        transaction.initialize();

        if(!transaction.isValid()) {
            throw new IllegalArgumentException("Invalid transaction data: check amount and description.");
        }

        return repository.save(transaction);
    }
}

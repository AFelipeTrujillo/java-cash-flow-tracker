package com.afelipetrujillo.cashFlowTracker.application.usecase;

import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import com.afelipetrujillo.cashFlowTracker.domain.valueobject.TransactionType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RegisterTransactionUseCase {

    private final TransactionRepository repository;
    private final GetBalanceUseCase getBalanceUseCase;

    public Transaction execute(Transaction transaction) {
        transaction.initialize();

        if (!transaction.isValid()) {
            throw new IllegalArgumentException("Invalid transaction data: check amount and description.");
        }

        if (transaction.getType() == TransactionType.EXPENSE) {
            BigDecimal currentBalance = getBalanceUseCase.execute();
            if (currentBalance.compareTo(transaction.getAmount()) < 0) {
                throw new IllegalStateException("Insufficient funds. Current balance: " + currentBalance);
            }
        }

        return repository.save(transaction);
    }
}

package com.afelipetrujillo.cashFlowTracker.infrastructure.rest;

import com.afelipetrujillo.cashFlowTracker.application.usecase.RegisterTransactionUseCase;
import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/transactions"})
@RequiredArgsConstructor
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        Transaction savedTransaction = registerTransactionUseCase.execute(transaction);
        return ResponseEntity.ok(savedTransaction);
    }
}

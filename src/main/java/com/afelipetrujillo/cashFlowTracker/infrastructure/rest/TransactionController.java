package com.afelipetrujillo.cashFlowTracker.infrastructure.rest;

import com.afelipetrujillo.cashFlowTracker.application.usecase.GetBalanceUseCase;
import com.afelipetrujillo.cashFlowTracker.application.usecase.RegisterTransactionUseCase;
import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping({"/api/transactions"})
@RequiredArgsConstructor
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;
    private final GetBalanceUseCase getBalanceUseCase;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        Transaction savedTransaction = registerTransactionUseCase.execute(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        return ResponseEntity.ok(getBalanceUseCase.execute());
    }
}

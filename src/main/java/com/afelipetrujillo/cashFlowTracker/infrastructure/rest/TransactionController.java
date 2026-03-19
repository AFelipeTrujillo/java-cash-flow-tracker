package com.afelipetrujillo.cashFlowTracker.infrastructure.rest;

import com.afelipetrujillo.cashFlowTracker.application.usecase.GetBalanceUseCase;
import com.afelipetrujillo.cashFlowTracker.application.usecase.RegisterTransactionUseCase;
import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping({"/api/transactions"})
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final RegisterTransactionUseCase registerTransactionUseCase;
    private final GetBalanceUseCase getBalanceUseCase;

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        log.info("TransactionController.create executed");
        Transaction savedTransaction = registerTransactionUseCase.execute(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        log.info("TransactionController.getBalance executed");
        return ResponseEntity.ok(getBalanceUseCase.execute());
    }
}

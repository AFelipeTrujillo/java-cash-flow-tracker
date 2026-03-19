package com.afelipetrujillo.cashFlowTracker.domain.repository;

import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    // Save or update a Transaction
    Transaction save(Transaction transaction);

    // Return a Transaction by id
    Optional<Transaction> findById(UUID id);

    // Return a list with all Transactions
    List<Transaction> findAll();

    // Delete by id
    void deleteById(UUID id);

    List<Transaction> findAllOrderByDateDesc();
}

package com.afelipetrujillo.cashFlowTracker.infrastructure.persistence;

import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final JpaTransactionRepository jpaRepository;

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaction> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private TransactionEntity toEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setDescription(domain.getDescription());
        entity.setAmount(domain.getAmount());
        entity.setType(domain.getType());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    private Transaction toDomain(TransactionEntity entity) {
        return Transaction.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .type(entity.getType())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

package com.afelipetrujillo.cashFlowTracker.infrastructure.persistence;

import com.afelipetrujillo.cashFlowTracker.domain.valueobject.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String description;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime createdAt;
}

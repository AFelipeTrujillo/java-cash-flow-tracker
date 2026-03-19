package com.afelipetrujillo.cashFlowTracker.domain.model;

import com.afelipetrujillo.cashFlowTracker.domain.valueobject.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private UUID id;
    private String description;
    private BigDecimal amount;
    private TransactionType type;
    private LocalDateTime createAt;

    public void initialize() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }

        if (this.createAt == null) {
            this.createAt = LocalDateTime.now();
        }
    }

    public boolean isValid() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0 && this.description != null;
    }

    /**
     * Returns the amount with its mathematical sign based on the transaction type.
     * Expenses are returned as negative values, while other types remain positive.
     * * @return BigDecimal The signed transaction amount.
     */
    public BigDecimal getSignedAmount() {
        return this.type == TransactionType.EXPENSE ? amount.negate() : amount;
    }


}

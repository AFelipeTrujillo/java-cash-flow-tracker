package com.afelipetrujillo.cashFlowTracker.application;

import com.afelipetrujillo.cashFlowTracker.application.usecase.GetBalanceUseCase;
import com.afelipetrujillo.cashFlowTracker.domain.model.Transaction;
import com.afelipetrujillo.cashFlowTracker.domain.repository.TransactionRepository;
import com.afelipetrujillo.cashFlowTracker.domain.valueobject.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetBalanceServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private GetBalanceUseCase getBalanceUseCase;

    @Test
    void execute_ShouldCalculateCorrectTotalBalance() {

        Transaction t1 = new Transaction();
        t1.initialize();
        t1.setDescription("Salary");
        t1.setAmount(new BigDecimal("1000.00"));
        t1.setType(TransactionType.INCOME);

        Transaction t2 = new Transaction();
        t2.initialize();
        t2.setDescription("Coffee");
        t2.setAmount(new BigDecimal("5.50"));
        t2.setType(TransactionType.EXPENSE);


        Transaction t3 = new Transaction();
        t3.initialize();
        t3.setDescription("Gym");
        t3.setAmount(new BigDecimal("40.00"));
        t3.setType(TransactionType.EXPENSE);


        when(repository.findAll()).thenReturn(List.of(t1, t2, t3));
        BigDecimal result = getBalanceUseCase.execute();
        BigDecimal expectedBalance = new BigDecimal("954.50");
        assertEquals(expectedBalance, result, "The balance should correctly sum incomes and subtract expenses");
    }


}

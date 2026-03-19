package com.afelipetrujillo.cashFlowTracker.infrastructure.rest;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setup() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleInsufficientFunds_ShouldReturnConflictStatus() {
        IllegalStateException exception = new IllegalStateException("Insufficient funds");

        ResponseEntity<String> response = handler.handleInsufficientFunds(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Insufficient funds", response.getBody());
    }

    @Test
    void handleBadRequest_ShouldReturnBadRequestStatus() {
        // Arrange
        IllegalArgumentException exception = new IllegalArgumentException("Invalid Data");

        // Act
        ResponseEntity<String> response = handler.handleBadRequest(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Data", response.getBody());
    }
}

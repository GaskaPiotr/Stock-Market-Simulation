package com.github.gaskapiotr.stockmarketsim.gateway;

import com.github.gaskapiotr.stockmarketsim.bank.BankStockQuantityIsZeroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BankStockNotFoundException.class)
    public ResponseEntity<String> handleBankStockNotFound(BankStockNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(BankStockQuantityIsZeroException.class)
    public ResponseEntity<String> handleBankStockQuantityIsZero(BankStockQuantityIsZeroException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}

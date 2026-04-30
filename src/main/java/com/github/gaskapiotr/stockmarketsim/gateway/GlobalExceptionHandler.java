package com.github.gaskapiotr.stockmarketsim.gateway;

import com.github.gaskapiotr.stockmarketsim.bank.BankStockInvalidQuantityException;
import com.github.gaskapiotr.stockmarketsim.bank.BankStockQuantityIsZeroException;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletNotFoundException;
import com.github.gaskapiotr.stockmarketsim.wallet.WalletStockNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BankStockNotFoundException.class)
    public ResponseEntity<String> handleBankStockNotFound(BankStockNotFoundException exception) {
        return notFound(exception);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> handleWalletNotFound(WalletNotFoundException exception) {
        return notFound(exception);
    }

    private ResponseEntity<String> notFound(RuntimeException exception) {
        return genericResponse(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> genericResponse(RuntimeException exception, HttpStatus status) {
        return ResponseEntity.status(status).body(exception.getMessage());
    }

    @ExceptionHandler(BankStockQuantityIsZeroException.class)
    public ResponseEntity<String> handleBankStockQuantityIsZero(BankStockQuantityIsZeroException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler(WalletStockNotFoundException.class)
    public ResponseEntity<String> handleWalletStockNotFound(WalletStockNotFoundException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler(BankStockInvalidQuantityException.class)
    public ResponseEntity<String> handleBankStockInvalidQuantity(BankStockInvalidQuantityException exception) {
        return badRequest(exception);
    }

    private ResponseEntity<String> badRequest(RuntimeException exception) {
        return genericResponse(exception, HttpStatus.BAD_REQUEST);
    }
}

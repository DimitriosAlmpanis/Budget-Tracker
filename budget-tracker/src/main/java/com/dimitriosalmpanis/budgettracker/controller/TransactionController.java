package com.dimitriosalmpanis.budgettracker.controller;

import com.dimitriosalmpanis.budgettracker.dto.TransactionDTO;
import com.dimitriosalmpanis.budgettracker.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/budgettracker/")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        System.out.println("TransactionController - createTransaction");
        TransactionDTO createdTransactionDTO = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(createdTransactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        System.out.println("TransactionController - getAllTransactions");
        List<TransactionDTO> transactionsDTO = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactionsDTO);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionDTO> getTransactionByID(@PathVariable("id") Long transactionID) {
        System.out.println("TransactionController - getTransactionById " + transactionID);
        TransactionDTO transactionDTO = transactionService.getTransactionByID(transactionID);
        return ResponseEntity.ok(transactionDTO);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable("id") Long transactionID,@RequestBody TransactionDTO updatedTransactionDTO) {
        System.out.println("TransactionController - updateTransaction");
        TransactionDTO transactionDTO = transactionService.updateTransaction(transactionID, updatedTransactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long transactionID) {
        System.out.println("TransactionController - deleteTransaction");
        transactionService.deleteTransaction(transactionID);
        return ResponseEntity.ok("Transaction deleted successfully.");
    }
}

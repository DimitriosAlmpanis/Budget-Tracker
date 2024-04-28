package com.dimitriosalmpanis.budgettracker.service;

import com.dimitriosalmpanis.budgettracker.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    List<TransactionDTO> getAllTransactions();
    TransactionDTO getTransactionByID(Long transactionID);
    TransactionDTO updateTransaction(Long transactionID, TransactionDTO updatedTransactionDTO);
    void deleteTransaction(Long transactionID);
}

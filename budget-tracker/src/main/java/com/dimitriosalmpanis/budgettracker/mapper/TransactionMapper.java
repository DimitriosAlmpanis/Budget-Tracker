package com.dimitriosalmpanis.budgettracker.mapper;

import com.dimitriosalmpanis.budgettracker.dto.TransactionDTO;
import com.dimitriosalmpanis.budgettracker.entity.Transaction;

public class TransactionMapper {

    public static TransactionDTO mapToTransactionDTO(Transaction transaction) {
        System.out.println("TransactionMapper - mapToTransactionDTO");
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getDescription(),
                CategoryMapper.mapToCategoryDTO(transaction.getCategory())
        );
    }

    public static Transaction mapToTransaction(TransactionDTO transactionDTO) {
        System.out.println("TransactionMapper - mapToTransaction");
        return new Transaction(
                transactionDTO.getId(),
                transactionDTO.getAmount(),
                transactionDTO.getDate(),
                transactionDTO.getDescription(),
                CategoryMapper.mapToCategory(transactionDTO.getCategory())
        );
    }
}

package com.dimitriosalmpanis.budgettracker.service.impl;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;
import com.dimitriosalmpanis.budgettracker.dto.TransactionDTO;

import com.dimitriosalmpanis.budgettracker.entity.Transaction;
import com.dimitriosalmpanis.budgettracker.exception.ResourceNotFoundException;
import com.dimitriosalmpanis.budgettracker.mapper.CategoryMapper;
import com.dimitriosalmpanis.budgettracker.mapper.TransactionMapper;
import com.dimitriosalmpanis.budgettracker.repository.TransactionRepository;
import com.dimitriosalmpanis.budgettracker.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        System.out.println("TransactionServiceImpl - createTransaction");
        System.out.println(transactionDTO.getId());
        System.out.println(transactionDTO.getAmount());
        System.out.println(transactionDTO.getDate());
        System.out.println(transactionDTO.getCategory());

        Transaction transaction = TransactionMapper.mapToTransaction(transactionDTO);
        Transaction createdTransaction = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDTO(createdTransaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map((transaction)-> TransactionMapper.mapToTransactionDTO(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getTransactionByID(Long transactionID) {
        Transaction transaction = transactionRepository.findById(transactionID)
                .orElseThrow(()-> new ResourceNotFoundException("Transaction does not exist with the id: " + transactionID));
        return TransactionMapper.mapToTransactionDTO(transaction);
    }

    @Override
    public TransactionDTO updateTransaction(Long transactionID, TransactionDTO updatedTransactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionID).orElseThrow(
                ()-> new ResourceNotFoundException("Transaction does not exist with the id: " + transactionID));

        transaction.setDate(updatedTransactionDTO.getDate());
        transaction.setAmount(updatedTransactionDTO.getAmount());
        transaction.setDescription(updatedTransactionDTO.getDescription());

        CategoryDTO updatedCategoryDTO = updatedTransactionDTO.getCategory();
        transaction.setCategory(CategoryMapper.mapToCategory(updatedCategoryDTO));

        Transaction updatedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.mapToTransactionDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long transactionID) {
        Transaction transaction = transactionRepository.findById(transactionID).orElseThrow(
                ()-> new ResourceNotFoundException("Transaction does not exist with the id: " + transactionID)
        );
        transactionRepository.deleteById(transactionID);
    }
}

package com.dimitriosalmpanis.budgettracker.repository;

import com.dimitriosalmpanis.budgettracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

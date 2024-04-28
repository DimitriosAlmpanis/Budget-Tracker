package com.dimitriosalmpanis.budgettracker.repository;

import com.dimitriosalmpanis.budgettracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

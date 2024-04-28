package com.dimitriosalmpanis.budgettracker.service;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByID(Long categoryID);
    CategoryDTO updateCategory(Long categoryID, CategoryDTO updatedCategoryDTO);
    void deleteCategory(Long categoryID);
}

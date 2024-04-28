package com.dimitriosalmpanis.budgettracker.mapper;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;
import com.dimitriosalmpanis.budgettracker.entity.Category;

public class CategoryMapper {
    public static CategoryDTO mapToCategoryDTO(Category category) {
        return new CategoryDTO(category.getId(),category.getName());
    }

    public static Category mapToCategory(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}

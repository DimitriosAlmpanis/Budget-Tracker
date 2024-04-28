package com.dimitriosalmpanis.budgettracker.service.impl;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;
import com.dimitriosalmpanis.budgettracker.entity.Category;
import com.dimitriosalmpanis.budgettracker.exception.ResourceNotFoundException;
import com.dimitriosalmpanis.budgettracker.mapper.CategoryMapper;
import com.dimitriosalmpanis.budgettracker.repository.CategoryRepository;
import com.dimitriosalmpanis.budgettracker.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.mapToCategory(categoryDTO);
        Category createdCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDTO(createdCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category)-> CategoryMapper.mapToCategoryDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByID(Long categoryID) {
        Category category = categoryRepository.findById(categoryID)
                .orElseThrow(()-> new ResourceNotFoundException("Category does not exist with the id: " + categoryID));
        return CategoryMapper.mapToCategoryDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryID, CategoryDTO updatedCategoryDTO) {
        Category category = categoryRepository.findById(categoryID).orElseThrow(
                ()-> new ResourceNotFoundException("Category does not exist with the id: " + categoryID));

        category.setName(updatedCategoryDTO.getName());

        Category updatedCategory = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryID) {
        Category category = categoryRepository.findById(categoryID).orElseThrow(
                ()-> new ResourceNotFoundException("Category does not exist with the id: " + categoryID)
        );
        categoryRepository.deleteById(categoryID);
    }
}

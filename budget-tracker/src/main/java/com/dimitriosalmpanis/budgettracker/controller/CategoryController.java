package com.dimitriosalmpanis.budgettracker.controller;

import com.dimitriosalmpanis.budgettracker.dto.CategoryDTO;
import com.dimitriosalmpanis.budgettracker.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/budgettracker/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        System.out.println("CategoryController - createCategory");
        CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        System.out.println("~~~~~~~~~~CategoryController getAllCategories~~~~~~~~~~");
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable("id") Long categoryID) {
        System.out.println("CategoryController - getCategoryById " + categoryID);
        CategoryDTO categoryDTO = categoryService.getCategoryByID(categoryID);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long categoryID,@RequestBody CategoryDTO updatedCategoryDTO) {
        CategoryDTO categoryDTO = categoryService.updateCategory(categoryID, updatedCategoryDTO);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryID) {
        categoryService.deleteCategory(categoryID);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}

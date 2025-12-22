package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.categoryDTO.CategoryCreateRequest;
import dev.v.expensetracker.dto.categoryDTO.CategoryResponse;
import dev.v.expensetracker.dto.categoryDTO.CategoryUpdateRequest;
import dev.v.expensetracker.repository.CategoryRepository;
import dev.v.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public CategoryRepository createCategory(@PathVariable Long userId,
                                             @Valid @RequestBody CategoryCreateRequest dto) {
        return categoryService.createCategory(userId, dto);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable Long categoryId,
                                           @Valid @RequestBody CategoryUpdateRequest dto) {
        return categoryService.updateCategory(categoryId, dto);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategory(@PathVariable Long categoryId) {
        return categoryService.readCategory(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}

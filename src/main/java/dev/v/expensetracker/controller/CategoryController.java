package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.categoryDTO.CategoryCreateRequest;
import dev.v.expensetracker.dto.categoryDTO.CategoryResponse;
import dev.v.expensetracker.dto.categoryDTO.CategoryUpdateRequest;
import dev.v.expensetracker.security.CustomUserDetails;
import dev.v.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public CategoryResponse createCategory(@AuthenticationPrincipal CustomUserDetails userDetails,
                                           @Valid @RequestBody CategoryCreateRequest dto) {
        Long userId = userDetails.getUser().getUserId();
        return categoryService.createCategory(userId, dto);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@AuthenticationPrincipal CustomUserDetails userDetails,
                                           @PathVariable Long categoryId,
                                           @Valid @RequestBody CategoryUpdateRequest dto) {
        Long userId = userDetails.getUser().getUserId();
        return categoryService.updateCategory(userId, categoryId, dto);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategory(@AuthenticationPrincipal CustomUserDetails userDetails,
                                        @PathVariable Long categoryId) {
        Long userId = userDetails.getUser().getUserId();
        return categoryService.readCategory(userId, categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @PathVariable Long categoryId) {
        Long userId = userDetails.getUser().getUserId();
        categoryService.deleteCategory(userId, categoryId);
    }
}

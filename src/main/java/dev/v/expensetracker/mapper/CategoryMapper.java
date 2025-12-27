package dev.v.expensetracker.mapper;

import dev.v.expensetracker.dto.categoryDTO.CategoryCreateRequest;
import dev.v.expensetracker.dto.categoryDTO.CategoryResponse;
import dev.v.expensetracker.dto.categoryDTO.CategoryUpdateRequest;
import dev.v.expensetracker.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponseDTO(Category category) {
        if (category == null) return null;

        CategoryResponse dto = new CategoryResponse();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());
        dto.setUserId(category.getUser().getUserId());

        return dto;
    }

    public Category toEntity(CategoryCreateRequest dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;
    }

    public void updateEntityFromDTO(CategoryUpdateRequest dto, Category category) {

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            category.setDescription(dto.getDescription());
        }
    }
}

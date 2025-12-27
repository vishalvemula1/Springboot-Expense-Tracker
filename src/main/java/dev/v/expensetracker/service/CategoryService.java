package dev.v.expensetracker.service;

import dev.v.expensetracker.dto.categoryDTO.CategoryCreateRequest;
import dev.v.expensetracker.dto.categoryDTO.CategoryResponse;
import dev.v.expensetracker.dto.categoryDTO.CategoryUpdateRequest;
import dev.v.expensetracker.entity.Category;
import dev.v.expensetracker.entity.User;
import dev.v.expensetracker.exception.ResourceNotFoundException;
import dev.v.expensetracker.mapper.CategoryMapper;
import dev.v.expensetracker.repository.CategoryRepository;
import dev.v.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(UserRepository userRepository,
                           CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;

    }

    @Transactional
    public CategoryResponse createCategory(Long userId, CategoryCreateRequest dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, "id", userId));

        Category category = categoryMapper.toEntity(dto);
        category.setUser(user);
        categoryRepository.save(category);


        return categoryMapper.toResponseDTO(category);

    }

    @Transactional
    public CategoryResponse updateCategory(Long userId,
                                           Long categoryId,
                                           CategoryUpdateRequest dto) {

        Category category = categoryRepository.findByUserUserIdAndCategoryId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", categoryId));
        categoryMapper.updateEntityFromDTO(dto, category);

        categoryRepository.save(category);

        return categoryMapper.toResponseDTO(category);
    }

    @Transactional
    public void deleteCategory(Long userId,
                               Long categoryId) {
        Category category = categoryRepository.findByUserUserIdAndCategoryId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", categoryId));

        categoryRepository.deleteById(category.getCategoryId());
    }

    @Transactional
    public CategoryResponse readCategory(Long userId,
                                         Long categoryId) {
        Category category = categoryRepository.findByUserUserIdAndCategoryId(userId, categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", categoryId));

        return categoryMapper.toResponseDTO(category);
    }
}

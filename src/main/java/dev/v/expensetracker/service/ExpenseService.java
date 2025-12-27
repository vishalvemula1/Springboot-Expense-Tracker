package dev.v.expensetracker.service;

import dev.v.expensetracker.dto.expenseDTO.ExpenseCreateRequest;
import dev.v.expensetracker.dto.expenseDTO.ExpenseResponse;
import dev.v.expensetracker.dto.expenseDTO.ExpenseUpdateRequest;
import dev.v.expensetracker.entity.Category;
import dev.v.expensetracker.entity.Expense;
import dev.v.expensetracker.entity.User;
import dev.v.expensetracker.exception.ResourceNotFoundException;
import dev.v.expensetracker.mapper.ExpenseMapper;
import dev.v.expensetracker.repository.CategoryRepository;
import dev.v.expensetracker.repository.ExpenseRepository;
import dev.v.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(UserRepository userRepository,
                          CategoryRepository categoryRepository,
                          ExpenseRepository expenseRepository,
                          ExpenseMapper expenseMapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;

    }



    @Transactional
    public ExpenseResponse createExpense(Long userId, ExpenseCreateRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class, "id", userId));

        Category category;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", dto.getCategoryId()));
        }
        else {
            category = categoryRepository.findByUserUserIdAndIsDefaultTrue(userId).orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", dto.getCategoryId()));
        }

        Expense expense = expenseMapper.toEntity(dto);
        expense.setUser(user);
        expense.setCategory(category);

        expenseRepository.save(expense);

        return expenseMapper.toResponseDTO(expense);

    }

    @Transactional
    public ExpenseResponse updateExpense(Long expenseId, ExpenseUpdateRequest dto) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(Expense.class, "id", expenseId));

        expenseMapper.updateEntityFromDTO(dto, expense);

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(Category.class, "id", dto.getCategoryId()));
            expense.setCategory(category);
        }

        expenseRepository.save(expense);

        return expenseMapper.toResponseDTO(expense);

    }

    @Transactional
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    @Transactional
    public ExpenseResponse readExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException(Expense.class, "id", expenseId));

        return expenseMapper.toResponseDTO(expense);
    }

}

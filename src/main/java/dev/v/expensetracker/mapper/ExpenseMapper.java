package dev.v.expensetracker.mapper;

import dev.v.expensetracker.dto.expenseDTO.ExpenseCreateRequest;
import dev.v.expensetracker.dto.expenseDTO.ExpenseResponse;
import dev.v.expensetracker.dto.expenseDTO.ExpenseUpdateRequest;
import dev.v.expensetracker.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseResponse toResponseDTO(Expense expense) {
        if (expense == null) return null;

        ExpenseResponse dto = new ExpenseResponse();
        dto.setExpenseId(expense.getExpenseId());
        dto.setName(expense.getName());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());
        dto.setCategoryId(expense.getCategory().getCategoryId());

        return dto;
    }

    public Expense toEntity(ExpenseCreateRequest dto) {
        if (dto == null) return null;

        Expense expense = new Expense();
        expense.setName(dto.getName());
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());

        return expense;
    }

    public void updateEntityFromDTO(ExpenseUpdateRequest dto, Expense expense) {
        if (dto.getName() != null) {
            expense.setName(dto.getName());
        }
        if(dto.getDescription() != null) {
            expense.setDescription(dto.getDescription());
        }
        if (dto.getAmount() != null) {
            expense.setAmount(dto.getAmount());
        }
    }
}

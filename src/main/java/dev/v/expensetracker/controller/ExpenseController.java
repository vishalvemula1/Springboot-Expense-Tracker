package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.expenseDTO.ExpenseCreateRequest;
import dev.v.expensetracker.dto.expenseDTO.ExpenseResponse;
import dev.v.expensetracker.dto.expenseDTO.ExpenseUpdateRequest;
import dev.v.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}/expenses/")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/")
    public ExpenseResponse createExpense(@PathVariable Long userId,
                                         @Valid @RequestBody ExpenseCreateRequest dto) {
        return expenseService.createExpense(userId, dto);
    }

    @PutMapping("/{expenseId}")
    public ExpenseResponse updateExpense(@PathVariable Long expenseId,
                                         @Valid @RequestBody ExpenseUpdateRequest dto) {
        return expenseService.updateExpense(expenseId, dto);
    }

    @GetMapping("/{expenseId}")
    public ExpenseResponse getExpense(@PathVariable Long expenseId) {
        return expenseService.readExpense(expenseId);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
    }
}

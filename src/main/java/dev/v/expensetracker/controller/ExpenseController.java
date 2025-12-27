package dev.v.expensetracker.controller;

import dev.v.expensetracker.dto.expenseDTO.ExpenseCreateRequest;
import dev.v.expensetracker.dto.expenseDTO.ExpenseResponse;
import dev.v.expensetracker.dto.expenseDTO.ExpenseUpdateRequest;
import dev.v.expensetracker.entity.User;
import dev.v.expensetracker.security.CustomUserDetails;
import dev.v.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/expenses/")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/")
    public ExpenseResponse createExpense(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @Valid @RequestBody ExpenseCreateRequest dto) {

        Long userId = userDetails.getUser().getUserId();
        return expenseService.createExpense(userId, dto);
    }

    @PutMapping("/{expenseId}")
    public ExpenseResponse updateExpense(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @PathVariable Long expenseId,
                                         @Valid @RequestBody ExpenseUpdateRequest dto) {
        Long userId = userDetails.getUser().getUserId();
        return expenseService.updateExpense(userId, expenseId, dto);
    }

    @GetMapping("/{expenseId}")
    public ExpenseResponse getExpense(@AuthenticationPrincipal CustomUserDetails userDetails,
                                      @PathVariable Long expenseId) {
        Long userId = userDetails.getUser().getUserId();
        return expenseService.readExpense(userId, expenseId);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@AuthenticationPrincipal CustomUserDetails userDetails,
                              @PathVariable Long expenseId) {
        Long userId = userDetails.getUser().getUserId();
        expenseService.deleteExpense(userId, expenseId);
    }
}

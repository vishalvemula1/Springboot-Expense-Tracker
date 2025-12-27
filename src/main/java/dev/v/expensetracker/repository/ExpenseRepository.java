package dev.v.expensetracker.repository;

import dev.v.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByUserUserIdAndExpenseId(Long userId, Long expenseId);

    List<Expense> findByUserUserId(Long userId);
}

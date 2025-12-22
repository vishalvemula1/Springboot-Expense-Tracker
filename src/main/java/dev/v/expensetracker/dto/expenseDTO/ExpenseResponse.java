package dev.v.expensetracker.dto.expenseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ExpenseResponse {

    private Long expenseId;
    private String name;
    private String description;
    private Long amount;
    private Instant createdAt;
    private Instant updatedAt;
    private Long categoryId;
}

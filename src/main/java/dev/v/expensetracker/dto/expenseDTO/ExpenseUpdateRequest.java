package dev.v.expensetracker.dto.expenseDTO;

import dev.v.expensetracker.validation.NotBlankIfPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ExpenseUpdateRequest {
    @NotBlankIfPresent
    @Size(min = 2, max = 50, message = "{validation.size.range}")
    private String name;

    @NotBlankIfPresent
    @PositiveOrZero(message = "{validation.positiveOrZero}")
    private Long amount;

    @NotBlankIfPresent
    @Size(max = 1000, message = "{validation.size.max}")
    private String description;

    @NotBlankIfPresent
    @Positive(message = "{validation.positive}")
    private Long categoryId;
}

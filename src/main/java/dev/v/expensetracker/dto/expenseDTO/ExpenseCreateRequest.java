package dev.v.expensetracker.dto.expenseDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ExpenseCreateRequest {

    @NotBlank(message = "{validation.notBlank}")
    @Size(min = 2, max = 50, message = "{validation.size.range}")
    private String name;

    @PositiveOrZero(message = "{validation.positiveOrZero}")
    private Long amount;

    @NotBlank(message = "{validation.notBlank}")
    @Size(max = 1000, message = "{validation.size.max}")
    private String description;

    @Positive(message = "{validation.positive}")
    private Long categoryId;
}

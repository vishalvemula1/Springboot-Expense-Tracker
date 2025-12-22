package dev.v.expensetracker.dto.categoryDTO;

import dev.v.expensetracker.validation.NotBlankIfPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryUpdateRequest {

    @NotBlankIfPresent
    @Size(min = 2, max = 20, message = "{validation.size.range}")
    private String name;

    @NotBlankIfPresent
    @Size(max = 1000, message = "{validation.size.max}")
    private String description;
}

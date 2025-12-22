package dev.v.expensetracker.dto.categoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CategoryCreateRequest {

    @NotBlank(message = "{validation.notBlank}")
    @Size(min = 2, max = 20, message = "{validation.size.range}")
    private String name;

    @NotBlank(message = "{validation.notBlank}")
    @Size(max = 1000, message = "{validation.size.max}")
    private String description;
}

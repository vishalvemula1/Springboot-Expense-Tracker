package dev.v.expensetracker.dto.categoryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Long userId;
}

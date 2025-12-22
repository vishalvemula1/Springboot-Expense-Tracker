package dev.v.expensetracker.dto.userDTO;

import dev.v.expensetracker.validation.NotBlankIfPresent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserUpdateRequest {

    @NotBlankIfPresent
    @Size(min = 3, max = 50, message = "{validation.size.range}")
    private String username;

    @NotBlankIfPresent
    @Email(message = "{validation.notValid}")
    @Size(min = 3, max = 50, message = "{validation.size.range}")
    private String email;

    @NotBlankIfPresent
    @Size(min = 8, message = "{validation.size.min}")
    private String password;
}

package dev.v.expensetracker.dto.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "{validation.notBlank}")
    @Size(min = 3, max = 50, message = "{validation.size.range}")
    private String username;

    @NotBlank(message = "{validation.notBlank}")
    @Email(message = "{validation.notValid}")
    @Size(min = 3, max = 50, message = "{validation.size.range}")
    private String email;

    @NotBlank(message = "{validation.notBlank}")
    @Size(min = 8, message = "{validation.size.min}")
    private String password;
}

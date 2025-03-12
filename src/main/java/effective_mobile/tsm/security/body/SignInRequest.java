package effective_mobile.tsm.security.body;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Sign in data")
public class SignInRequest {
    @Schema(description = "User email", example = "mail@mail.com")
    @NotNull(message = "Login must be not null")
    private String email;
    @Schema(description = "User password", example = "1pass1")
    @NotNull(message = "Password must be not null")
    private String password;
}

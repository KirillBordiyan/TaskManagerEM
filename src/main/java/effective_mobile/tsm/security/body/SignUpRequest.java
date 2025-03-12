package effective_mobile.tsm.security.body;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Sign up data")
public class SignUpRequest {
    @Schema(description = "Username, important", example = "Ivan Ivanovich")
    @NotNull(message = "Username must be not null.")
    private String username;
    @Schema(description = "User email", example = "random@mail.com")
    @NotNull(message = "Login must be not null.")
    private String email;
    @Schema(description = "Password", example = "1pas2")
    @NotNull(message = "Password must be not null.")
    private String password;
}

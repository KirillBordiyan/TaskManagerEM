package effective_mobile.tsm.security.body;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpRequest {
    @NotNull(message = "Username must be not null.")
    private String username;
    @NotNull(message = "Login must be not null.")
    private String email;
    @NotNull(message = "Password must be not null.")
    private String password;
}

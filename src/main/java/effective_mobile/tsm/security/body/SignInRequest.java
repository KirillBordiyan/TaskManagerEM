package effective_mobile.tsm.security.body;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequest {
    @NotNull(message = "Login must be not null.")
    private String email;
    @NotNull(message = "Password must be not null.")
    private String password;
}

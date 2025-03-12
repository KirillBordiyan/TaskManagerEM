package effective_mobile.tsm.security.body;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Jwt response",
        description = "Use for hand over to frontend jwt and main user info")
public class JwtResponse {
    @Schema(description = "User ID", example = "be910e74-96b5-4fbf-8342-1585e2f9ff67")
    private UUID userId;
    @Schema(description = "User email", example = "random@mail.com")
    private String email;
    @Schema(description = "Access toke", example = "eyJhbGciOiJIUz...")
    private String access;
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUz...")
    private String refresh;
}

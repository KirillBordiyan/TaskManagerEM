package effective_mobile.tsm.security.body;

import effective_mobile.tsm.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class JwtRequest {
    private UUID userId;
    private Role role;
}

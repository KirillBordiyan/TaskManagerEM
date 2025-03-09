package effective_mobile.tsm.security.body;

import effective_mobile.tsm.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private UUID userId;
    private String email;
    private String access;
    private String refresh;
}

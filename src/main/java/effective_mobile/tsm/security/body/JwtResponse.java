package effective_mobile.tsm.security.body;

import effective_mobile.tsm.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String access;
    private String refresh;
}

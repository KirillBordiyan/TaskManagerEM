package effective_mobile.tsm.security.body;

import effective_mobile.tsm.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtDecode {
    private UUID userId;
    private Role role;
}

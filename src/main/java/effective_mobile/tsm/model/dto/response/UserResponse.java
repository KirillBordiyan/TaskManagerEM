package effective_mobile.tsm.model.dto.response;

import effective_mobile.tsm.model.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID userId;
    private String name;
    private String email;
    private List<TaskResponse> principalOf;
    private List<TaskResponse> executorOf;
    private Role role;
}

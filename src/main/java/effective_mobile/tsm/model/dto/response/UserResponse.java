package effective_mobile.tsm.model.dto.response;

import effective_mobile.tsm.model.entity.user.Role;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "User response body")
public class UserResponse {
    @Schema(description = "User ID", example = "be910e74-96b5-4fbf-8342-1585e2f9ff67")
    private UUID userId;
    @Schema(description = "Username", example = "Ivan Ivanovich")
    private String username;
    @Schema(description = "Email", example = "random@mail.com")
    private String email;
    @ArraySchema(
            schema = @Schema(implementation = TaskResponse.class),
            arraySchema = @Schema(description = "List of task responses where user is Principal")
    )
    private List<TaskResponse> principalOf;
    @ArraySchema(
            schema = @Schema(implementation = TaskResponse.class),
            arraySchema = @Schema(description = "List of task responses where user is Executor")
    )
    private List<TaskResponse> executorOf;
    @Schema(description = "User role", example = "USER")
    private Role role;
}

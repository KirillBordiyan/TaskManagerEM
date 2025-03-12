package effective_mobile.tsm.service;

import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.SignUpRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getEntityById(UUID userId);
    User getEntityByEmail(String email);
    User getEntityByUsername(String username);
    UserResponse getUserById(UUID userId);
    UserResponse getUserByUsername(String username);
    UserResponse getUserById(String userId);
    UserResponse createUser(SignUpRequest input);
    UserResponse updateUserData(UUID userId, UserUpdateInput updatedData);
    void deleteUser(UUID userId);
    boolean isPrincipal(UUID userId, UUID taskId);
    boolean isExecutor(UUID userId, UUID taskId);
//    List<TaskResponse> getAllTasksLikeExecutor(UUID userId);
//    List<TaskResponse> getAllTasksLikePrincipal(UUID userId);
}

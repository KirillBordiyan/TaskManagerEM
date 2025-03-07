package effective_mobile.tsm.service;

import effective_mobile.tsm.model.dto.input.UserCreateInput;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.JwtDecode;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getEntityById(UUID userId);
    User getEntityByEmail(String email);
    UserResponse getUserById(UUID userId);
    UserResponse getUserById(String userId);
    UserResponse createUser(UserCreateInput input);
    UserResponse updateUserData(UUID userId, UserUpdateInput updatedData, JwtDecode decode);
    void deleteUser(UUID userId, JwtDecode decode);
    boolean isPrincipal(UUID taskId, JwtDecode decode);
    boolean isExecutor(UUID taskId, JwtDecode decode);
    List<TaskResponse> getAllTasksLikeExecutor(UUID userId);
    List<TaskResponse> getAllTasksLikePrincipal(UUID userId);
}

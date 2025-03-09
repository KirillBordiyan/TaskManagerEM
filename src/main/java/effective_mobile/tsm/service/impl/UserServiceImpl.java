package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getEntityById(UUID userId) {
        return null;
    }

    @Override
    public User getEntityByEmail(String email) {
        return null;
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return null;
    }

    @Override
    public UserResponse getUserById(String userId) {
        return null;
    }

    @Override
    public UserResponse createUser(SignUpRequest input) {
        return null;
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse updateUserData(UUID userId, UserUpdateInput updatedData, String jwt) {
        return null;
    }

    @Override
    public void deleteUser(UUID userId, String jwt) {

    }

    @Override
    public boolean isPrincipal(UUID taskId, String jwt) {
        return false;
    }

    @Override
    public boolean isExecutor(UUID taskId, String jwt) {
        return false;
    }

    @Override
    public List<TaskResponse> getAllTasksLikeExecutor(UUID userId) {
        return List.of();
    }

    @Override
    public List<TaskResponse> getAllTasksLikePrincipal(UUID userId) {
        return List.of();
    }
}

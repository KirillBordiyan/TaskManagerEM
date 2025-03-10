package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.exceptions.model.AccessDeniedException;
import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.model.entity.user.Role;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.repositories.TaskRepository;
import effective_mobile.tsm.repositories.UserRepository;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import effective_mobile.tsm.util.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @Override
    public User getEntityById(UUID userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found (by id)"));
    }

    @Override
    public User getEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found (by email)"));
    }

    @Override
    public UserResponse getUserById(UUID userId) {
        return userMapper.mappingUserEntityToResponse(
                userRepository.findUserById(userId)
                        .orElseThrow(() -> new RequestedResourceNotFound("User response not found (by id)"))
        );
    }

    @Override
    public UserResponse getUserById(String userId) {
        return getUserById(UUID.fromString(userId));
    }

    @Override
    public UserResponse createUser(SignUpRequest input) {

        if (Objects.isNull(input.getEmail())) {
            throw new IllegalStateException("CREATE: user email must be not null");
        }
        if (Objects.isNull(input.getUsername())) {
            throw new IllegalStateException("CREATE: username must be not null");
        }
        if (Objects.isNull(input.getPassword())) {
            throw new IllegalStateException("CREATE: password must be not null");
        }

        User user = new User();
        user.setEmail(input.getEmail());
        user.setUsername(input.getUsername());
        user.setPassword(encoder.encode(input.getPassword()));
        user.setExecutorOf(new ArrayList<>());
        user.setPrincipalOf(new ArrayList<>());
        user.setRole(Role.USER);

        User saved = userRepository.save(user);

        return userMapper.mappingUserEntityToResponse(saved);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        return userMapper.mappingUserEntityToResponse(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new RequestedResourceNotFound("User response not found (by username)")));
    }

    @Override
    public UserResponse updateUserData(UUID userId, UserUpdateInput updatedData, String jwt) {
        if (!jwtService.isTokenValid(jwt)) {
            throw new RuntimeException("Invalid token");
        }
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found in update"));

        if(updatedData.getUpdatedEmail() != null){
            user.setEmail(updatedData.getUpdatedEmail());
        }
        if(updatedData.getUpdatedName() != null){
            user.setUsername(updatedData.getUpdatedName());
        }

        User saved = userRepository.save(user);
        return userMapper.mappingUserEntityToResponse(saved);
    }

    @Override
    public void deleteUser(UUID userId, String jwt) {
        if (!jwtService.isTokenValid(jwt)) {
            throw new RuntimeException("Invalid token");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public boolean isPrincipal(UUID taskId, String jwt) {
        if (!jwtService.isTokenValid(jwt)) {
            throw new RuntimeException("Invalid token");
        }
        UUID userId = jwtService.extractUserId(jwt);
        return userRepository.isPrincipal(userId, taskId);
    }

    @Override
    public boolean isExecutor(UUID taskId, String jwt) {
        if (!jwtService.isTokenValid(jwt)) {
            throw new RuntimeException("Invalid token");
        }
        UUID userId = jwtService.extractUserId(jwt);
        return userRepository.isExecutor(userId, taskId);
    }

    @Override
    public List<TaskResponse> getAllTasksLikeExecutor(UUID userId) {

        return taskRepository.findAllByUserIdExecutor(userId)
                .stream()
                .map(taskMapper::mappingTaskEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getAllTasksLikePrincipal(UUID userId) {
        return taskRepository.findAllByUserIdPrincipal(userId)
                .stream().map(taskMapper::mappingTaskEntityToResponse)
                .collect(Collectors.toList());
    }
}

package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.model.entity.user.Role;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.repositories.TaskRepository;
import effective_mobile.tsm.repositories.UserRepository;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import effective_mobile.tsm.util.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public User getEntityById(UUID userId) {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found (by id)"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getEntityByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found (by email)"));
    }

    @Override
    @Transactional(readOnly = true)
    public User getEntityByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RequestedResourceNotFound("User not found (by username)"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {
        return userMapper.mappingUserEntityToResponse(
                userRepository.findUserByUserId(userId)
                        .orElseThrow(() -> new RequestedResourceNotFound("User response not found (by id)"))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(String userId) {
        return getUserById(UUID.fromString(userId));
    }

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        return userMapper.mappingUserEntityToResponse(
                userRepository.findUserByUsername(username)
                        .orElseThrow(() -> new RequestedResourceNotFound("User response not found (by username)")));
    }

    @Override
    @Transactional
    public UserResponse updateUserData(UUID userId, UserUpdateInput updatedData) {

        User user = userRepository.findUserByUserId(userId)
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
    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isPrincipal(UUID userId, UUID taskId) {
        return userRepository.isPrincipal(userId, taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExecutor(UUID userId, UUID taskId) {
        return userRepository.isExecutor(userId, taskId);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<TaskResponse> getAllTasksLikeExecutor(UUID userId) {
//
//        return taskRepository.findAllByUserIdExecutor(userId)
//                .stream()
//                .map(taskMapper::mappingTaskEntityToResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<TaskResponse> getAllTasksLikePrincipal(UUID userId) {
//        return taskRepository.findAllByUserIdPrincipal(userId)
//                .stream().map(taskMapper::mappingTaskEntityToResponse)
//                .collect(Collectors.toList());
//    }
}

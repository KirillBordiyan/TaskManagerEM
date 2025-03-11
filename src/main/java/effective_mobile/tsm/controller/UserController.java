package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(@RequestHeader("Authorization") String jwtAccess) {
        UUID userId = jwtService.extractUserId(jwtAccess);
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUserData(@PathVariable UUID userId,
                                       @RequestBody UserUpdateInput updateInput){
        return userService.updateUserData(userId, updateInput);
    }
}

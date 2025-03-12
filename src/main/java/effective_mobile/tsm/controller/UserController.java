package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UUID userId = userService.getEntityByEmail(userDetails.getUsername()).getUserId();
        return userService.getUserById(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{userId}")
    public UserResponse updateUserData(@PathVariable UUID userId,
                                       @RequestBody UserUpdateInput updateInput){
        return userService.updateUserData(userId, updateInput);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
    }
}

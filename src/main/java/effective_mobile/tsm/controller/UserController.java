package effective_mobile.tsm.controller;

import effective_mobile.tsm.exceptions.ExceptionBody;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.UserUpdateInput;
import effective_mobile.tsm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Manage users endpoints")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Get UserResponse by username",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @Operation(
            summary = "Get current user",
            description = "Use like a home page",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isOwnerOrSenderByUsername(#userDetails.getUsername())")
    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UUID userId = userService.getEntityByEmail(userDetails.getUsername()).getUserId();
        return userService.getUserById(userId);
    }

    @Operation(
            summary = "Update user data",
            description = "Change main data in user profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isOwnerOrSenderById(#userId) or @CSE.isAdminOrSudo()")
    @PutMapping("/{userId}")
    public UserResponse updateUserData(@PathVariable UUID userId,
                                       @Valid @RequestBody UserUpdateInput updateInput){
        return userService.updateUserData(userId, updateInput);
    }

    @Operation(
            summary = "Delete user by user ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isAdminOrSudo()")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId){
        userService.deleteUser(userId);
    }
}

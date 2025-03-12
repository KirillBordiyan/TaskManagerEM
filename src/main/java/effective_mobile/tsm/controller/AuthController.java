package effective_mobile.tsm.controller;

import effective_mobile.tsm.exceptions.ExceptionBody;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.security.auth.AuthService;
import effective_mobile.tsm.security.body.JwtResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization Controller", description = "Auth users plus generate admin-user")
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "SignUp",
            description = "Registration point",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))
                    )
            }
    )
    @PermitAll
    @PostMapping("/signup")
    public UserResponse signUp(@Valid @RequestBody SignUpRequest loginData) {
        return authService.register(loginData);
    }

    @Operation(
            summary = "SignIn",
            description = "Login process point",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JwtResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PermitAll
    @PostMapping("/signin")
    public JwtResponse signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @Operation(
            summary = "Refresh",
            description = "Refresh users jwts",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JwtResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PermitAll
    @PostMapping("/refresh")
    public JwtResponse refresh(@Valid @RequestHeader("Authorization") String refresh){
        return authService.refresh(refresh);
    }

    @Operation(
            summary = "Admins",
            description = "Creating admins by superadmin",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isSudo()")
    @PostMapping("/admins")
    public UserResponse makeAdmin(@Valid @RequestBody SignUpRequest adminData){
        return authService.makeAdmin(adminData);
    }
}

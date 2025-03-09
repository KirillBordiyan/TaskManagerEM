package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.security.auth.AuthService;
import effective_mobile.tsm.security.body.JwtResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public UserResponse signUp(@RequestBody SignUpRequest loginData) {
        return authService.login(loginData);
    }

    @PostMapping("/signin")
    public JwtResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    //TODO изменить логику refresh и signIn, ибо я не возвращаю данные для повторного запроса нужные в методе (пароль)
    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestHeader("Authorization") String refresh){
        return authService.refresh(refresh);
    }
}

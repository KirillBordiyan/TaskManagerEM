package effective_mobile.tsm.security.auth;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.security.body.JwtResponse;

public interface AuthService {
    UserResponse login(SignUpRequest login);
    JwtResponse refresh(String refreshToken);
    JwtResponse signIn(SignInRequest login);
}

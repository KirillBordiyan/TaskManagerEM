package effective_mobile.tsm.security.auth;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.security.body.JwtResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;

public interface AuthService {
    UserResponse register(SignUpRequest login);
    UserResponse makeAdmin(SignUpRequest adminData);
    JwtResponse refresh(String refreshToken);
    JwtResponse signIn(SignInRequest login);
}

package effective_mobile.tsm.security.auth;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.security.body.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public UserResponse login(SignUpRequest login) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }

    @Override
    public JwtResponse signIn(SignInRequest login) {
        return null;
    }
}

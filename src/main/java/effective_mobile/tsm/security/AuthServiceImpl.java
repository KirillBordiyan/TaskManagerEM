package effective_mobile.tsm.security;

import effective_mobile.tsm.security.body.JwtRequest;
import effective_mobile.tsm.security.body.JwtResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public JwtResponse login(JwtRequest login) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}

package effective_mobile.tsm.security;

import effective_mobile.tsm.security.body.JwtRequest;
import effective_mobile.tsm.security.body.JwtResponse;
import org.springframework.stereotype.Service;

public interface AuthService {
    JwtResponse login(JwtRequest login);
    JwtResponse refresh(String refreshToken);
}

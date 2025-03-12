package effective_mobile.tsm.security.auth;

import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.JwtResponse;
import effective_mobile.tsm.security.body.SignInRequest;
import effective_mobile.tsm.security.body.SignUpRequest;
import effective_mobile.tsm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager manager;
    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public JwtResponse signIn(SignInRequest login) {
        JwtResponse response = new JwtResponse();
        manager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        User user = userService.getEntityByEmail(login.getEmail());

        response.setUserId(user.getUserId());
        response.setEmail(user.getEmail());
        response.setAccess(jwtService.generateAccessToken(user));
        response.setRefresh(jwtService.generateRefreshTokenToken(user));

        return response;
    }

    @Override
    public UserResponse register(SignUpRequest login) {
        return userService.createUser(login);
    }

    @Override
    public UserResponse makeAdmin(SignUpRequest adminData) {
        return userService.makeAdmin(adminData);
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtService.refreshUserToken(refreshToken);
    }
}

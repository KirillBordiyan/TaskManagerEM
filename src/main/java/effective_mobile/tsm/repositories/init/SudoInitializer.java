package effective_mobile.tsm.repositories.init;

import effective_mobile.tsm.config.SudoProperties;
import effective_mobile.tsm.model.entity.user.Role;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SudoInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SudoProperties properties;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        try {
            User sudo = new User();
            sudo.setUsername(properties.getSudoUsername());
            sudo.setEmail(properties.getSudoEmail());
            sudo.setPassword(encoder.encode(properties.getSudoPassword()));
            sudo.setExecutorOf(new ArrayList<>());
            sudo.setPrincipalOf(new ArrayList<>());
            sudo.setRole(Role.SUDO);

            userRepository.save(sudo);
        } catch (Exception e) {
            throw new RuntimeException("Sudo creating exception: \n" + e.getMessage());
        }
    }
}

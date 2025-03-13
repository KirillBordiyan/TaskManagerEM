package effective_mobile.tsm.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.sudo")
public class SudoProperties {

    private String sudoUsername;
    private String sudoEmail;
    private String sudoPassword;

    public SudoProperties(String sudoUsername, String sudoEmail, String sudoPassword) {
        this.sudoUsername = sudoUsername;
        this.sudoEmail = sudoEmail;
        this.sudoPassword = sudoPassword;
    }
}

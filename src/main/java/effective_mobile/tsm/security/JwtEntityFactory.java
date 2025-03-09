package effective_mobile.tsm.security;

import effective_mobile.tsm.model.entity.user.Role;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.security.body.JwtEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtEntityFactory {
    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                toGrantedAuth(List.of(user.getRole()))
        );
    }

    private static List<SimpleGrantedAuthority> toGrantedAuth(List<Role> roles) {

        ArrayList<SimpleGrantedAuthority> objects = new ArrayList<>();
        return Stream.of(roles)
                .map(Object::toString)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}

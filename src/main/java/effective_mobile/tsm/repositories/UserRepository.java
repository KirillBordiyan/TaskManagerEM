package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID userId);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean isPrincipal(UUID userId, UUID taskId);
    boolean isExecutor(UUID userId, UUID taskId);
}

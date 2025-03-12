package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByUserId(UUID userId);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT COUNT(up) > 0
                    FROM user_principal_of up
                    WHERE up.user_id = :userId AND up.task_id = :taskId
                    """
    )
    boolean isPrincipal(UUID userId, UUID taskId);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT COUNT(ue) > 0
                    FROM user_executor_of ue
                    WHERE ue.user_id = :userId AND ue.task_id = :taskId
                    """
    )
    boolean isExecutor(UUID userId, UUID taskId);
}

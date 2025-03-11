package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

//    @Query(
//            nativeQuery = true,
//            value = """
//                    SELECT u.user_id, u.username, u.email,u.password, up.task_id, ue.task_id ,u.role
//                    FROM users u
//                    LEFT JOIN user_executor_of ue on u.user_id = ue.user_id
//                    INNER JOIN user_principal_of up on u.user_id = up.user_id
//                    WHERE u.user_id = :userId be910e74-96b5-4fbf-8342-1585e2f9ff67
//                    """)
    Optional<User> findUserByUserId(UUID userId);

//    @Query(
//            nativeQuery = true,
//            value = """
//                    SELECT u.user_id, u.username, u.email,u.password, up.task_id, ue.task_id ,u.role
//                    FROM users u
//                    LEFT JOIN user_executor_of ue on u.user_id = ue.user_id
//                    INNER JOIN user_principal_of up on u.user_id = up.user_id
//                    WHERE u.username = :username
//                    """)
    Optional<User> findUserByUsername(String username);

//    @Query(
//            nativeQuery = true,
//            value = """
//                    SELECT u.user_id, u.username, u.email,u.password, up.task_id, ue.task_id ,u.role
//                    FROM users u
//                    LEFT JOIN user_executor_of ue on u.user_id = ue.user_id
//                    INNER JOIN user_principal_of up on u.user_id = up.user_id
//                    WHERE u.email = :email
//                    """)
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

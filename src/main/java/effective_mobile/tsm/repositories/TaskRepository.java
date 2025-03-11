package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query(nativeQuery = true,
            value = """
                    SELECT t.task_id, t.title, t.description, t.status,t.priority, t.created_at
                    FROM task_data t
                    WHERE t.task_id =:taskId
                    """)
    Optional<Task> findTaskById(UUID taskId);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT *
                    FROM task_data td
                    WHERE td.principal_id=:userId
                    """
    )
    List<Task> findAllByUserIdPrincipal(UUID userId);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT * 
                    FROM task_data td
                    WHERE td.executor_id =:userId
                    """
    )
    List<Task> findAllByUserIdExecutor(UUID userId);

    //TODO связывание через set поля запросом к бд
//    void assignToUserById(UUID taskId, UUID userId);
}

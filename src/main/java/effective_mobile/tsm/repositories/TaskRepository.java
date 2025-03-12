package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Optional<Task> findTaskByTaskId(UUID taskId);

    @Modifying
    @Query(value = "DELETE FROM user_executor_of WHERE task_id = :taskId", nativeQuery = true)
    void deleteTaskExecutorLinks(@Param("taskId") UUID taskId);

    @Modifying
    @Query(value = "DELETE FROM user_principal_of WHERE task_id = :taskId", nativeQuery = true)
    void deleteTaskPrincipalLinks(@Param("taskId") UUID taskId);

    //TODO связывание через set поля запросом к бд
//    void assignToUserById(UUID taskId, UUID userId);
}

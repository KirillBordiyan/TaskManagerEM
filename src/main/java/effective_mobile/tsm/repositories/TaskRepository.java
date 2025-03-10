package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findTaskById(UUID taskId);
    List<Task> findAllByUserIdPrincipal(UUID userId);
    List<Task> findAllByUserIdExecutor(UUID userId);
    //TODO связывание через set поля запросом к бд
    void assignToUserById(UUID taskId, UUID userId);
}

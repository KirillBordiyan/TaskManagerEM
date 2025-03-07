package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskCommentRepository extends JpaRepository<Comment, UUID> {
    Optional<Comment> findCommentById(UUID commentId);
}

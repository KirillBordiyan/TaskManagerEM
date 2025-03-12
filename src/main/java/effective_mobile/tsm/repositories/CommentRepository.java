package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Comment;
import effective_mobile.tsm.model.entity.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Optional<Comment> findByCommentId(UUID commentId);

    List<Comment> findAllByTask(Task task);

    @Query(
            nativeQuery = true,
            value = """
                    SELECT COUNT(up) > 0
                    FROM comment_data cd
                    WHERE cd.comment_user_id = :userId AND cd.comment_id = :commentId
                    """
    )
    boolean isCommentOwner(UUID userId, UUID commentId);
}

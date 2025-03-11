package effective_mobile.tsm.repositories;

import effective_mobile.tsm.model.entity.task.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query(nativeQuery = true,
    value = """
            SELECT c.comment_id, c.comment_content, c.created_at, c.commentOwner, c.task 
            FROM comment_data c
            left join users u on c.comment_owner = u.user_id
            left join tasks t on c.task = t.task_id
            where c.comment_id = :commentId
            """)
    Optional<Comment> findCommentById(UUID commentId);
}

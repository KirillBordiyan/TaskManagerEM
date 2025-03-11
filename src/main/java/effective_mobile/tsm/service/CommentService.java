package effective_mobile.tsm.service;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.model.entity.task.Comment;

import java.util.List;
import java.util.UUID;


public interface CommentService {
    Comment getComment(UUID commentId);
    CommentResponse getCommentById(UUID commentId);
    CommentResponse createComment(UUID userId, CommentCreateInput dto);
    CommentResponse updateComment(UUID commentId, CommentUpdateInput updatedComment);
    List<CommentResponse> getCommentsByTaskId(UUID taskId);
    void deleteComment(UUID commentId);
}

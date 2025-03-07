package effective_mobile.tsm.service;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.model.entity.task.Comment;
import effective_mobile.tsm.security.body.JwtDecode;

import java.util.UUID;


public interface CommentService {
    Comment getComment(UUID commentId);
    CommentResponse getCommentById(UUID commentId);
    CommentResponse createComment(UUID userId, CommentCreateInput dto);
    CommentResponse updateComment(UUID commentId, CommentUpdateInput updatedComment, JwtDecode decode);
    void deleteComment(UUID commentId, JwtDecode decode);
}

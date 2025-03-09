package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.model.entity.task.Comment;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment getComment(UUID commentId) {
        return null;
    }

    @Override
    public CommentResponse getCommentById(UUID commentId) {
        return null;
    }

    @Override
    public CommentResponse createComment(UUID userId, CommentCreateInput dto) {
        return null;
    }

    @Override
    public CommentResponse updateComment(UUID commentId, CommentUpdateInput updatedComment, JwtDecode decode) {
        return null;
    }

    @Override
    public List<CommentResponse> getCommentsByTaskId(UUID taskId) {
        return List.of();
    }

    @Override
    public void deleteComment(UUID commentId, UUID userId) {

    }
}

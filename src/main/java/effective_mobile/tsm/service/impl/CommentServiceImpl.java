package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.model.entity.task.Comment;
import effective_mobile.tsm.repositories.CommentRepository;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskService taskService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public Comment getComment(UUID commentId) {
        return commentRepository.findCommentById(commentId)
                .orElseThrow(() -> new RequestedResourceNotFound("Comment not found (by id)"));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponse getCommentById(UUID commentId) {
        return commentMapper.mappingCommentEntityToResponse(getComment(commentId));
    }

    @Override
    @Transactional
    public CommentResponse createComment(UUID userId, CommentCreateInput dto) {
        Comment comment = commentMapper.mappingCommentInputToEntity(dto);
        comment.setCreatedAt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        comment.setCommentOwner(userService.getEntityById(userId));
        comment.setTask(taskService.getTaskById(dto.getTaskId()));
        return commentMapper.mappingCommentEntityToResponse(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentResponse updateComment(UUID commentId, CommentUpdateInput updatedComment) {
        Comment comment = commentRepository.findCommentById(commentId)
                .orElseThrow(() -> new RequestedResourceNotFound("Comment not found (in update)"));
        if(updatedComment.getCommentContent() != null){
            comment.setCommentContent(updatedComment.getCommentContent());
        }
        return commentMapper.mappingCommentEntityToResponse(commentRepository.save(comment));
    }


    @Override
    @Transactional
    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByTaskId(UUID taskId) {
        return List.of();
    }
}

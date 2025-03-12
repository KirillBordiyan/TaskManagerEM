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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class CommentServiceImpl implements CommentService {

    private final TaskService taskService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public Comment getComment(UUID commentId) {
        return commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RequestedResourceNotFound("Comment not found (by id)"));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponse getCommentById(UUID commentId) {
        return commentMapper.mappingCommentEntityToResponse(getComment(commentId));
    }

    @Override
    @Transactional
    public CommentResponse createComment(UUID userId, UUID taskId, CommentCreateInput dto) {
        Comment comment = commentMapper.mappingCommentInputToEntity(dto);
        comment.setCreatedAt(LocalDateTime.parse(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        comment.setCommentOwner(userService.getEntityById(userId));
        comment.setTask(taskService.getTaskById(taskId));
        return commentMapper.mappingCommentEntityToResponse(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentResponse updateComment(UUID commentId, CommentUpdateInput updatedComment) {
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RequestedResourceNotFound("Comment not found (in update)"));
        if(updatedComment.getUpdatedCommentContent() != null){
            comment.setCommentContent(updatedComment.getUpdatedCommentContent());
        }
        return commentMapper.mappingCommentEntityToResponse(commentRepository.save(comment));
    }


    @Override
    @Transactional
    public void deleteComment(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public boolean isCommentOwner(UUID userId, UUID commentId) {
        return commentRepository.isCommentOwner(userId, commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByTaskId(UUID taskId) {
        return commentRepository.findAllByTask(taskService.getTaskById(taskId)).stream()
                .map(commentMapper::mappingCommentEntityToResponse)
                .collect(Collectors.toList());
    }
}

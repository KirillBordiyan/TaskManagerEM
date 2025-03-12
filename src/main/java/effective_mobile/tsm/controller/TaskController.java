package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable UUID taskId) {
        return taskService.getTaskResponseById(taskId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{taskId}/comments")
    public CommentResponse createComment(@PathVariable UUID taskId,
                                         @RequestBody CommentCreateInput commentInput,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.createComment(
                userService.getEntityByEmail(
                        userDetails.getUsername()).getUserId(),
                taskId,
                commentInput);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{taskId}/comments")
    public List<CommentResponse> getComments(@PathVariable UUID taskId) {
        return taskService.getAllComments(taskId);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{taskId}/{commentId}")
    public CommentResponse updateComment(@PathVariable UUID taskId,
                                         @PathVariable UUID commentId,
                                         @RequestBody CommentUpdateInput updateInput){
        return commentService.updateComment(commentId, updateInput);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID taskId,
                              @PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
    }
}

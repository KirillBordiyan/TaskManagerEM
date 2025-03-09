package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;
    private final JwtService jwtService;

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable UUID taskId) {
        return taskService.getTaskResponseById(taskId);
    }

    @PostMapping("/{taskId}/comments")
    public CommentResponse createComment(@PathVariable UUID taskId,
                                         @RequestBody CommentCreateInput commentInput,
                                         @RequestHeader("Authorization") String jwtAccess) {
        UUID uuid = jwtService.extractUserId(jwtAccess);
        return commentService.createComment(uuid, commentInput);
    }

    @GetMapping("/{taskId}/comments")
    public List<CommentResponse> getComments(@PathVariable UUID taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }

    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID taskId,
                              @PathVariable UUID commentId,
                              @RequestHeader("Authorization") String jwtAccess) {
        UUID uuid = jwtService.extractUserId(jwtAccess);
        commentService.deleteComment(commentId, uuid);
    }
}

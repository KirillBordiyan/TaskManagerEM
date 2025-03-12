package effective_mobile.tsm.controller;

import effective_mobile.tsm.exceptions.ExceptionBody;
import effective_mobile.tsm.model.dto.input.CommentCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.CommentUpdateInput;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Controller", description = "To make and change comments")
public class TaskController {

    private final TaskService taskService;
    private final CommentService commentService;
    private final UserService userService;

    @Operation(
            summary = "Get task by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable UUID taskId) {
        return taskService.getTaskResponseById(taskId);
    }

    @Operation(
            summary = "Create comment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CommentResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isExecutor(#taskId) or @CSE.isPrincipal(#taskId) or @CSE.isAdminOrSudo()")
    @PostMapping("/{taskId}/comments")
    public CommentResponse createComment(@PathVariable UUID taskId,
                                         @Valid @RequestBody CommentCreateInput commentInput,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return commentService.createComment(
                userService.getEntityByEmail(
                        userDetails.getUsername()).getUserId(),
                taskId,
                commentInput);
    }

    @Operation(
            summary = "Get all comments on task",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CommentResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{taskId}/comments")
    public List<CommentResponse> getComments(@PathVariable UUID taskId) {
        return taskService.getAllComments(taskId);
    }

    @Operation(
            summary = "Update comment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CommentResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isCommentOwner(#commentId)")
    @PutMapping("/{taskId}/{commentId}")
    public CommentResponse updateComment(@PathVariable UUID taskId,
                                         @PathVariable UUID commentId,
                                         @RequestBody CommentUpdateInput updateInput){
        return commentService.updateComment(commentId, updateInput);
    }

    @Operation(
            summary = "Delete comment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isTaskOwner(#taskId) or @CSE.isCommentOwner(#commentId) or @CSE.isAdminOrSudo()")
    @DeleteMapping("/{taskId}/comments/{commentId}")
    public void deleteComment(@PathVariable UUID taskId,
                              @PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
    }
}

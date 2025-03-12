package effective_mobile.tsm.controller;

import effective_mobile.tsm.exceptions.ExceptionBody;
import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.ExecutorTaskUpdateInput;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{username}/tasks")
@RequiredArgsConstructor
@Tag(name = "User-Task Controller", description = "Main manage of tasks")
public class UserTaskController {

    private final TaskService taskService;
    private final UserService userService;

    @Operation(
            summary = "Create task",
            description = "Create new task from user, who make request",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class))),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isOwnerOrSenderByUsername(#username)")
    @PostMapping
    public TaskResponse createTask(@PathVariable String username,
                                   @Valid @RequestBody TaskCreateInput taskInput) {
        return taskService.createTask(username, taskInput);
    }

    @Operation(
            summary = "Update task",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isOwnerOrSenderByUsername(#username) or @CSE.isAdminOrSudo()")
    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable String username,
                                   @PathVariable UUID taskId,
                                   @Valid @RequestBody TaskUpdateInput updateInput) {
        return taskService.updateTask(taskId, updateInput);
    }

    @Operation(
            summary = "Update task only by executor",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isExecutor(#taskId)")
    @PostMapping("/{taskId}")
    public TaskResponse updateStatusOnly(@PathVariable String username,
                                         @PathVariable UUID taskId,
                                         @Valid @RequestBody ExecutorTaskUpdateInput input) {
        return taskService.updateTaskByExecutor(taskId, input);
    }

    @Operation(
            summary = "Get list of tasks",
            description = "Tasks where current user is PRINCIPAL",
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
    @GetMapping("/principal")
    public List<TaskResponse> getTasksByUserPrincipal(@PathVariable String username) {
        UserResponse userByUsername = userService.getUserByUsername(username);
        return userByUsername.getPrincipalOf();
    }

    @Operation(
            summary = "Get list of tasks",
            description = "Tasks where current user is EXECUTOR",
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
    @GetMapping("/executor")
    public List<TaskResponse> getTasksByUserExecutor(@PathVariable String username) {
        UserResponse userByUsername = userService.getUserByUsername(username);
        return userByUsername.getExecutorOf();
    }

    @Operation(
            summary = "Delete task",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(schema = @Schema())),
                    @ApiResponse(
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionBody.class)))
            }
    )
    @PreAuthorize("@CSE.isOwnerOrSenderByUsername(#username) or @CSE.isAdminOrSudo()")
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable String username,
                           @PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }

}

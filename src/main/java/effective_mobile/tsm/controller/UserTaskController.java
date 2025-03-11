package effective_mobile.tsm.controller;

import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.response.UserResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.security.JwtService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{username}/tasks")
@RequiredArgsConstructor
public class UserTaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping
    public List<TaskResponse> getTasksByUserPrincipal(@PathVariable String username) {
        UserResponse userByUsername = userService.getUserByUsername(username);
        return userByUsername.getPrincipalOf();
    }

    //TODO вопрос, что передавать
    @PostMapping
    public TaskResponse createTask(@PathVariable String username,
                                   @RequestBody TaskCreateInput taskInput) {
        return taskService.createTask(username, taskInput);
    }

    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable String username,
                                   @PathVariable UUID taskId,
                                   @RequestBody TaskUpdateInput updateInput) {
        return taskService.updateTask(taskId, updateInput);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable String username,
                           @PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }

}

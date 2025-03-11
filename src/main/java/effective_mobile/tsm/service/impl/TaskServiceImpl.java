package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.model.entity.task.Task;
import effective_mobile.tsm.repositories.TaskRepository;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final CommentService commentService;

    @Override
    public Task getTaskById(UUID taskId) {
        return taskRepository.findTaskById(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task not found (by id)"));
    }

    @Override
    public TaskResponse createTask(String username, TaskCreateInput dto) {
        Task task = taskMapper.mappingTaskInputToEntity(dto);
        task.setComments(new ArrayList<>());
        task.setCreatedAt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        task.setPrincipal(userService.getEntityByUsername(username));
        task.setExecutor(userService.getEntityById(dto.getExecutorId()));
        return taskMapper.mappingTaskEntityToResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponse getTaskResponseById(UUID taskId) {
        return taskMapper.mappingTaskEntityToResponse(taskRepository.findTaskById(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task response not found (by id)")));
    }

    @Override
    public TaskResponse updateTask(UUID taskId, TaskUpdateInput updatedData) {
        Task task = taskRepository.findTaskById(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task not found (in update)"));
        if (updatedData.getUpdatedDescription() != null) {
            task.setDescription(updatedData.getUpdatedDescription());
        }
        if (updatedData.getUpdatedExecutor() != null) {
            task.setExecutor(updatedData.getUpdatedExecutor());
        }
        if (updatedData.getUpdatedTitle() != null) {
            task.setTitle(updatedData.getUpdatedTitle());
        }
        if (updatedData.getUpdatedPriority() != null) {
            task.setPriority(updatedData.getUpdatedPriority());
        }
        if (updatedData.getUpdatedStatus() != null) {
            task.setStatus(updatedData.getUpdatedStatus());
        }
        return taskMapper.mappingTaskEntityToResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<CommentResponse> getAllComments(UUID taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }
}

package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.model.entity.task.Task;
import effective_mobile.tsm.model.entity.user.User;
import effective_mobile.tsm.repositories.TaskRepository;
import effective_mobile.tsm.service.CommentService;
import effective_mobile.tsm.service.TaskService;
import effective_mobile.tsm.service.UserService;
import effective_mobile.tsm.util.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Task getTaskById(UUID taskId) {
        return taskRepository.findTaskByTaskId(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task not found (by id)"));
    }

    @Override
    @Transactional
    public TaskResponse createTask(String username, TaskCreateInput dto) {
        Task task = taskMapper.mappingTaskInputToEntity(dto);
        task.setComments(new ArrayList<>());
        task.setCreatedAt(LocalDateTime.parse(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        //FIXME не связываются сущности создателя и исполнителя в связанные таблички бд
        User principal = userService.getEntityByUsername(username);
        task.setPrincipal(principal);
        User executor = userService.getEntityById(dto.getExecutorId());
        task.setExecutor(executor);

        Task saved = taskRepository.save(task);
        principal.getPrincipalOf().add(saved);
        executor.getExecutorOf().add(saved);


        return taskMapper.mappingTaskEntityToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTaskResponseById(UUID taskId) {
        return taskMapper.mappingTaskEntityToResponse(taskRepository.findTaskByTaskId(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task response not found (by id)")));
    }

    @Override
    @Transactional
    public TaskResponse updateTask(UUID taskId, TaskUpdateInput updatedData) {
        Task task = taskRepository.findTaskByTaskId(taskId)
                .orElseThrow(() -> new RequestedResourceNotFound("Task not found (in update)"));
        if (updatedData.getUpdatedDescription() != null) {
            task.setDescription(updatedData.getUpdatedDescription());
        }
        if (updatedData.getUpdatedExecutor() != null) {
            task.setExecutor(userService.getEntityById(updatedData.getUpdatedExecutor()));
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
    @Transactional
    public void deleteTask(UUID taskId) {
        taskRepository.deleteTaskExecutorLinks(taskId);
        taskRepository.deleteTaskPrincipalLinks(taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getAllComments(UUID taskId) {
        return commentService.getCommentsByTaskId(taskId);
    }
}

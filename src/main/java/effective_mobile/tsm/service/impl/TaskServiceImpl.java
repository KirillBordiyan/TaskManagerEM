package effective_mobile.tsm.service.impl;

import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.model.entity.task.Task;
import effective_mobile.tsm.security.body.JwtDecode;
import effective_mobile.tsm.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public Task getTaskById(UUID taskId) {
        return null;
    }

    @Override
    public TaskResponse createTask(String username, TaskCreateInput dto, JwtDecode decode) {
        return null;
    }

    @Override
    public TaskResponse getTaskResponseById(UUID taskId) {
        return null;
    }

    @Override
    public TaskResponse updateTask(UUID taskId, TaskUpdateInput updatedData, JwtDecode decode) {
        return null;
    }

    @Override
    public void deleteTask(UUID taskId, JwtDecode decode) {

    }

    @Override
    public List<CommentResponse> getAllComments(UUID taskId) {
        return List.of();
    }
}

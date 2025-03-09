package effective_mobile.tsm.service;

import effective_mobile.tsm.model.dto.input.TaskCreateInput;
import effective_mobile.tsm.model.dto.response.CommentResponse;
import effective_mobile.tsm.model.dto.response.TaskResponse;
import effective_mobile.tsm.model.dto.update.TaskUpdateInput;
import effective_mobile.tsm.model.entity.task.Task;
import effective_mobile.tsm.security.body.JwtDecode;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    //TODO 2 метода на проверку владельца и исполнителя?
    Task getTaskById(UUID taskId);
    TaskResponse createTask(String username, TaskCreateInput dto, JwtDecode decode);
    TaskResponse getTaskResponseById(UUID taskId);
    TaskResponse updateTask(UUID taskId, TaskUpdateInput updatedData, JwtDecode decode);
    void deleteTask(UUID taskId, JwtDecode decode);
    List<CommentResponse> getAllComments(UUID taskId);
}

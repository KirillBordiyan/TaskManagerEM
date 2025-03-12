package effective_mobile.tsm.model.dto.response;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private UUID taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private List<CommentResponse> comments;
    private UUID principal;
    private UUID executor;
}

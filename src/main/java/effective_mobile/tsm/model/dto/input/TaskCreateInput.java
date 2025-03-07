package effective_mobile.tsm.model.dto.input;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateInput {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private UUID executorId;
}

package effective_mobile.tsm.model.dto.input;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Title must be not null")
    private String title;
    private String description;
    @NotNull(message = "Status must be not null")
    private TaskStatus status;
    @NotNull(message = "Priority mustBe not null")
    private TaskPriority priority;
    @NotNull(message = "Executor must be not null")
    private UUID executorId;
}

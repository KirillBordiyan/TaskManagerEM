package effective_mobile.tsm.model.dto.input;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Task create input", description = "To create new task")
public class TaskCreateInput {
    @Schema(description = "Task title", example = "Title of task")
    @NotNull(message = "Title must be not null")
    private String title;
    @Schema(description = "Task description", example = "In this case you must to do...")
    private String description;
    @Schema(description = "Task status", allowableValues = {"TODO", "IN_PROGRESS", "DONE"})
    @NotNull(message = "Status must be not null")
    private TaskStatus status;
    @Schema(description = "Task priority", allowableValues = {"HIGH", "MEDIUM", "LOW"})
    @NotNull(message = "Priority must be not null")
    private TaskPriority priority;
    @Schema(description = "Executor user id", example = "be910e74-96b5-4fbf-8342-1585e2f9ff67")
    @NotNull(message = "Executor must be not null")
    private UUID executorId;
}

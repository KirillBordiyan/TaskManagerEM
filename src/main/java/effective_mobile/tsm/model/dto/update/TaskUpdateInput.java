package effective_mobile.tsm.model.dto.update;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Task update input", description = "Data to update task")
public class TaskUpdateInput {
    @Schema(description = "New title", example = "Prev title was replaced")
    private String updatedTitle;
    @Schema(description = "New description", example = "P.S. I forgot to say smth")
    private String updatedDescription;
    @Schema(description = "New task status", allowableValues = {"TODO", "IN_PROGRESS", "DONE"})
    private TaskStatus updatedStatus;
    @Schema(description = "New task priority", allowableValues = {"HIGH", "MEDIUM", "LOW"})
    private TaskPriority updatedPriority;
    @Schema(description = "New executor user id", example = "be910e74-96b5-4fbf-8342-1585e2f9ff67")
    private UUID updatedExecutor;
}

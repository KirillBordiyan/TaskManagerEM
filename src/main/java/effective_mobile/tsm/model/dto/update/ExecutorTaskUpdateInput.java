package effective_mobile.tsm.model.dto.update;

import effective_mobile.tsm.model.entity.task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExecutorTaskUpdateInput {
    @Schema(description = "New task status", allowableValues = {"TODO", "IN_PROGRESS", "DONE"})
    @NotNull(message = "Updated status from executor must be not null")
    private TaskStatus updatedStatus;
}

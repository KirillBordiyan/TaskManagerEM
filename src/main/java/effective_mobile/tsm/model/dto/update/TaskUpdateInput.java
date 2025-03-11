package effective_mobile.tsm.model.dto.update;

import effective_mobile.tsm.model.entity.task.TaskPriority;
import effective_mobile.tsm.model.entity.task.TaskStatus;
import effective_mobile.tsm.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateInput {
    private String updatedTitle;
    private String updatedDescription;
    private TaskStatus updatedStatus;
    private TaskPriority updatedPriority;
    private User updatedExecutor;
}

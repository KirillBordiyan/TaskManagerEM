package effective_mobile.tsm.model.entity.task;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TaskPriority implements Serializable {
    HIGH, MEDIUM, LOW;
}

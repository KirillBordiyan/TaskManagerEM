package effective_mobile.tsm.model.entity.task;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum TaskStatus implements Serializable {
    TODO, IN_PROGRESS, DONE;
}

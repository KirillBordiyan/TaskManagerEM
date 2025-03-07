package effective_mobile.tsm.model.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import effective_mobile.tsm.model.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
@Entity
@Table
 */
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"principal", "executor"})
public class Task implements Serializable {

    private UUID taskId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private List<Comment> comments;
    private User principal;
    private User executor;

    //TODO убрать дальше
    public Task(String title,
                String description,
                TaskStatus status,
                TaskPriority priority,
                User principal,
                User executor) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.comments = new ArrayList<>();
        this.principal = principal;
        this.executor = executor;
    }
}

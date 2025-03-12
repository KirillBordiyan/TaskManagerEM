package effective_mobile.tsm.model.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import effective_mobile.tsm.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = " task_data", schema ="tasklist")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"principal", "executor"})
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private UUID taskId;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name ="status", nullable = false)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private TaskPriority priority;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "task_comment",
            joinColumns = @JoinColumn(name ="task_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name = "principal_id", nullable = false)
    private User principal;
    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
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

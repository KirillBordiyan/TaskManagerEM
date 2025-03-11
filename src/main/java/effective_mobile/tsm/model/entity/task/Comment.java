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
import java.util.UUID;


@Entity
@Table(name = "comment_data", schema = "tasklist")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"task", "commentOwner"})
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id")
    private UUID commentId;
    @Column(name = "comment_content")
    private String commentContent;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "comment_user_id", nullable = false)
    private User commentOwner;
    @ManyToOne
    @JoinColumn(name = "comment_task_id", nullable = false)
    private Task task;

    //TODO убрать дальше
    public Comment(String commentContent, User commentOwner, Task task) {
        this.commentContent = commentContent;
        this.createdAt = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.commentOwner = commentOwner;
        this.task = task;
    }


}

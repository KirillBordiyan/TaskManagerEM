package effective_mobile.tsm.model.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import effective_mobile.tsm.model.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/*
@Entity
@Table
 */
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"task"})
public class Comment implements Serializable {
    private UUID commentId;
    private String commentContent;
    private LocalDateTime createdAt;
    private User commentOwner;
    private Task task;

    //TODO убрать дальше
    public Comment(String commentContent, User commentOwner, Task task) {
        this.commentContent = commentContent;
        this.createdAt = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.commentOwner = commentOwner;
        this.task = task;
    }


}

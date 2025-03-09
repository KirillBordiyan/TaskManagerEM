package effective_mobile.tsm.model.entity.user;

import effective_mobile.tsm.model.entity.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/*
@Entity
@Table
 */
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    private UUID userId;
    private String username;
    private String email;
    private String password;
    private List<Task> principalOf;
    private List<Task> executorOf;
    private Role role;
}

package effective_mobile.tsm.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import effective_mobile.tsm.model.entity.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users", schema = "tasklist")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"principalOf", "executorOf"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_principal_of",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="task_id")
    )
    private List<Task> principalOf;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_executor_of",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> executorOf;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}

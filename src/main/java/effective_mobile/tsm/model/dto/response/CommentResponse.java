package effective_mobile.tsm.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentResponse {
    private UUID commentId;
    private UUID commentOwner;
    private UUID task;
    private String commentContent;
    private LocalDateTime createdAt;
}

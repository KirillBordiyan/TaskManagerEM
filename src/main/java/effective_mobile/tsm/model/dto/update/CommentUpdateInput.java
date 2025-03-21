package effective_mobile.tsm.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Comment update input", description = "To update comment data")
public class CommentUpdateInput {
    @Schema(description = "Updated data", example = "I can do it!")
    @NotNull(message = "Updated comment content must be not null")
    private String updatedCommentContent;
}

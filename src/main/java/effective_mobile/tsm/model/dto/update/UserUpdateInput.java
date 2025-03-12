package effective_mobile.tsm.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User update input")
public class UserUpdateInput {
    @Schema(description = "You may update your name", example = "Oleg Olegovich")
    private String updatedName;
    @Schema(description = "Updated email and you must login again", example = "newemail@mail.com")
    private String updatedEmail;
}

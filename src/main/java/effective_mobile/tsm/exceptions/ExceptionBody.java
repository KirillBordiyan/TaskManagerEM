package effective_mobile.tsm.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Schema(name = "Exception body")
public class ExceptionBody {
    @Override
    public String toString() {
        return "message='" + message + '\'' +
                ", errors=" + errors;
    }

    @Schema(description = "Message", example = "Validation exception or Internal server error")
    private String message;
    @Schema(description = "Additional props")
    private Map<String, String> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}

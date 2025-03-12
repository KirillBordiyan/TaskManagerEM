package effective_mobile.tsm.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionBody {
    private String message;
    private Map<String, String> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}

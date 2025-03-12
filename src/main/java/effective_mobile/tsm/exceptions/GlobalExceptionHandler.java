package effective_mobile.tsm.exceptions;

import effective_mobile.tsm.exceptions.model.CustomAccessDeniedException;
import effective_mobile.tsm.exceptions.model.RequestedResourceNotFound;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestedResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleRequestResourceNoFound(RequestedResourceNotFound ex){
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler({CustomAccessDeniedException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handleAccessDenied(){
        return new ExceptionBody("Access denied");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIllegalState(IllegalStateException ex){
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        ExceptionBody body = new ExceptionBody("Validation failed");
        List<FieldError> err = ex.getBindingResult().getFieldErrors();
        body.setErrors(err.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage)));

        return body;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleConstrainsViolation(ConstraintViolationException ex){
        ExceptionBody body = new ExceptionBody("Validation failed");
        body.setErrors(ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        v -> v.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                )));
        return body;
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleSignatureEx(SignatureException ex){
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleAuthentication(AuthenticationException ex){
        return new ExceptionBody("Authentication failed");
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleException(Exception ex){
        return new ExceptionBody("Internal -> " + ex.getMessage());
    }
}

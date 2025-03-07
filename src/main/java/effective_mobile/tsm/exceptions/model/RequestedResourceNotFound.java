package effective_mobile.tsm.exceptions.model;

public class RequestedResourceNotFound extends RuntimeException{
    public RequestedResourceNotFound(String message) {
        super(message);
    }
}

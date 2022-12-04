package jinny.study.springboot.springpractice.exception;

import jinny.study.springboot.springpractice.struct.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    public CustomException(String message) {
        this(message, null);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract ErrorCode getErrorCode();

    public abstract HttpStatus getHttpStatus();

    public abstract String getMessage();
}

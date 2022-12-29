package jinny.study.springboot.springpractice.exception;

import jinny.study.springboot.springpractice.struct.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidValueException extends CustomException {

    private ErrorCode code = ErrorCode.INVALID_PARAMETERS;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String message;

    public InvalidValueException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public ErrorCode getErrorCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

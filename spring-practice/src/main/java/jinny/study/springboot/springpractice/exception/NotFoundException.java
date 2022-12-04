package jinny.study.springboot.springpractice.exception;

import jinny.study.springboot.springpractice.struct.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    private ErrorCode code = ErrorCode.NOT_FOUND;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public ErrorCode getErrorCode() {
        return this.code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

package jinny.study.springboot.springpractice.exception;

import jinny.study.springboot.springpractice.struct.ErrorCode;
import org.springframework.http.HttpStatus;

public class CheckThrowException extends Exception {

    private ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message;

    public CheckThrowException(String message) {
        super(message);
    }

}

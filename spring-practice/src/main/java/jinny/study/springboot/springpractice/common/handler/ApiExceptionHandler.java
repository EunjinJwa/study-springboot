package jinny.study.springboot.springpractice.common.handler;

import jinny.study.springboot.springpractice.exception.CustomException;
import jinny.study.springboot.springpractice.struct.ErrorCode;
import jinny.study.springboot.springpractice.struct.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> validationParametersNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.INVALID_PARAMETERS)
                .message(e.getMessage())
                .description(request.getRequestURI())

                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> InternalExceptionHandler(CustomException e) {
        System.out.println("httpStatus == " + e.getHttpStatus());

        ErrorResponse response = ErrorResponse.builder()
                .code(e.getErrorCode())
                .message(e.getMessage())
                .description("")
                .build();

        log.error("InternalErrorException | {} | ", response.toString(), e);
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException e) {
        ErrorResponse response = ErrorResponse.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR)
                .message(e.toString())
                .description("")
                .build();

        log.error("RuntimeException | {} | ", response.toString(), e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

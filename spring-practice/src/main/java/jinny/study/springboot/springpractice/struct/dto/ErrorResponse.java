package jinny.study.springboot.springpractice.struct.dto;

import jinny.study.springboot.springpractice.struct.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ErrorResponse {

    private ErrorCode code;
    private String message;
    private String description;



}

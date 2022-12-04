package jinny.study.springboot.springpractice.web;

import jinny.study.springboot.springpractice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/test1")
    public ResponseEntity<Object> runtimeException() {
        try {
            String value = "kassy";
            Integer intValue = Integer.parseInt(value);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test2")
    public ResponseEntity<Object> customErrorException() {
        callCustomError();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void callCustomError() {
        throw new NotFoundException("kassy");
    }


}

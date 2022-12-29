package jinny.study.springboot.springpractice.web;

import jinny.study.springboot.springpractice.exception.NotFoundException;
import jinny.study.springboot.springpractice.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/error")
public class ErrorController {

    private final ExceptionService exceptionService;


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

    @GetMapping("/test3")
    public ResponseEntity<Object> test3(@RequestParam Integer a,
                                        @RequestParam Integer b) {

        exceptionService.sumTest(a, b);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test4")
    public ResponseEntity<Object> test4(@RequestParam Integer a,
                                        @RequestParam Integer b) {

        exceptionService.divTest(a, b);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

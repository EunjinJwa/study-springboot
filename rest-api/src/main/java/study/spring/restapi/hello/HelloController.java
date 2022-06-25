package study.spring.restapi.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity hello() {
        return ResponseEntity.ok("Hello! Welcome.");
    }


}

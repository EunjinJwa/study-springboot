package study.spring.restapi.events;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;

@Controller
public class EventController {

    @PostMapping("/api/events")
    public ResponseEntity createEvent() {

//        URI uri = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(EventController.class).createEvent()).slash("{id").toUri();
        return ResponseEntity.created(null).build();    // created 응답을 보낼때는 항상 uri거 있어야 함
    }

}

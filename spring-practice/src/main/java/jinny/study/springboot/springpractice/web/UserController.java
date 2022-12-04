package jinny.study.springboot.springpractice.web;

import jinny.study.springboot.springpractice.domain.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public void createUser(@Validated @RequestBody User user, BindingResult br) {
        System.out.println(user);
        if (br.hasErrors()) {
            System.out.println("validation Error");
            return;
        }
        System.out.println("Pass");
    }
}

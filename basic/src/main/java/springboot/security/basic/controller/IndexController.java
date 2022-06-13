package springboot.security.basic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.security.basic.model.User;
import springboot.security.basic.repository.UserRepository;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping(value = "/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping(value = "/loginForm")
    public String login() {
        return "loginForm";
    }

    @GetMapping(value = "/joinForm")
    public String join() {
        return "joinForm";
    }

    @PostMapping(value = "/join")
    public String join(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/loginForm";
    }

    @GetMapping(value = "/joinProc")
    public @ResponseBody
    String joinProc() {
        return "회원가입 완료";
    }


}

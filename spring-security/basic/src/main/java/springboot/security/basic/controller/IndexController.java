package springboot.security.basic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.security.basic.config.auth.PrincipalDetails;
import springboot.security.basic.model.User;
import springboot.security.basic.repository.UserRepository;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String loginInfo(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("/test/login ==========");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String result = new StringBuffer("세션 정보 확인하기").append("\n")
                .append("authentication User : ").append(principalDetails.getUser())
                .append("userDetails User : ").append(userDetails.getUser())
                .toString();
        return result;
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String oauthLoginInfo(Authentication authentication,
                                               @AuthenticationPrincipal OAuth2User oAuth) {
        System.out.println("/test/login ==========");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String result = new StringBuffer("세션 정보 확인하기 \n").append("\n")
                .append("authentication User : ").append(oAuth2User.getAttributes())
                .append("oauth2User User : ").append(oAuth.getAttributes())
                .toString();
        return result;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/user")
    public @ResponseBody
    String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails : " + principalDetails.getUser());
        return "user";
    }

    @GetMapping(value = "/manager")
    public @ResponseBody
    String manager() {
        return "manager";
    }

    @GetMapping(value = "/admin")
    public @ResponseBody
    String admin() {
        return "admin";
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

    @Secured("ROLE_ADMIN")
    @GetMapping(value = "/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    // @PreAuthorize : 메서드가 실행되기 전에 권한 검사
    // 권한 여러개 설정할때는 preAuthorize 표현이 더 좋음. (@Secured 보다)
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/data")
    public @ResponseBody String data() {
        return "데이터 정보";
    }
}

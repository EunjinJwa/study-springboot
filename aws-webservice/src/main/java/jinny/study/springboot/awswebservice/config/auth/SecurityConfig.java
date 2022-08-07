package jinny.study.springboot.awswebservice.config.auth;

import jinny.study.springboot.awswebservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 disable 시킴.
                .and()
                .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션으 ㅣ시작점이다. antMatchers 옵션을 사용하기위해 선언되어야함.
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()   // 나머지 URL들은 모두 인증된 사용자들에게만 허용
                .and()
                .logout()
                    .logoutSuccessUrl("/")// 로그아웃 성공시 이동할 URL
                .and()
                .oauth2Login()  // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                    .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당함
                    .userService(customOAuth2UserService);  // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록한다. 소셜 로그인을 통해 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시할 수 있음



    }
}

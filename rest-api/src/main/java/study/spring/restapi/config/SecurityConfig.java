package study.spring.restapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import study.spring.restapi.accounts.AccountService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;

    /**
     * oauth 토큰을 저장하는 저장소
     * @return
     */
    @Bean
    public TokenStore tokenStore () {
        return new InMemoryTokenStore();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 스프링 시큐리티가 적용되지 않을 요청을 정의함
     * @param web
     * @throws Exception
     */
    @Override
    public void configure (WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/hello");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());    // 정적 리소스
    }

    /**
     * form 인증 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.anonymous()
                .and()
                .formLogin()
                .and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/hello/**").anonymous()     // GET /api/** 는 누구에게나 허용함
                .anyRequest().authenticated();      // 나머지 요청은 인증 필요
    }
}

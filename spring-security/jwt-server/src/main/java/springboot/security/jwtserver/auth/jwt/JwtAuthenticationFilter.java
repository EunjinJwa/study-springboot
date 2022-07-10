package springboot.security.jwtserver.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음
 *  /login 요청해서 username, password 를 전송(Post)하면
 *  JwtAuthenticationFilter 가 동작함.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /**
     * /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
     *
     * 1. username, password 받아서 정상인지 로그인 시도를 해본다.
     * 2. authenticationManager 로 로그인시도를 하면 PrincipalDetailsService가 호출됨. (PrincipalDetailsService#loadUserByUsername())
     * 3. PrncipalDetails를 세션에 담고 (권한 관리를 위해 session에 담아줌)
     * 4. JWT토큰을 만들어서 응답해주면 됨.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도 중");

        return super.attemptAuthentication(request, response);
    }
}

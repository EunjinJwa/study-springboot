package springboot.security.jwtserver.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springboot.security.jwtserver.auth.PrincipalDetails;
import springboot.security.jwtserver.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

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

        try {
//            BufferedReader br = request.getReader();
//            String input = null;
//            while ((input = br.readLine()) != null) {
//                System.out.println(input);
//            }

            ObjectMapper om = new ObjectMapper();   // json 데이터를 파싱해줌
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            /** PrincipalDetailService의 loadUserByUsername() 함수가 실행됨
             * 인증이 되면(loadUserByUsername()결과가 정상이면) authentication에 로그인한 정보가 담겨서 리턴됨.
             * */
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);
            /** authentication 객체가 session 영역에 저장됨 => 로그인이 되었다는 뜻*/
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUser().getUsername());

            /** authentication를 리턴해줌으로써 authentication 객체가 session영역에 저장되고, 권한 관리를 security가 대신 수행할 수 있게 됨.
             * JWT 토큰을 사용하면 굳이 세션을 만들 이유는 없으나, 권한 처리때문에 session에 넣어 준다.
             * */
            return authentication;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
     * JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 response해주면 됨.
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println(">>> successfulAuthentication() : 인증 완료");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60000*10))    // 만료 시간 (10분)
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())    // withClaim : 내가 토큰에 넣고싶은 value값을 넣으면 됨.
                .sign(Algorithm.HMAC512(JwtProperties.SECRET_KEY));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}

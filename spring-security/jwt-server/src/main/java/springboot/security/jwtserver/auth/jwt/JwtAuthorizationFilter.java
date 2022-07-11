package springboot.security.jwtserver.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import springboot.security.jwtserver.auth.PrincipalDetails;
import springboot.security.jwtserver.model.User;
import springboot.security.jwtserver.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 시큐리티가 filter를 가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있음.
 * 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음
 * 만약 권한이나 인증ㅇ ㅣ필요한 주소가 아니라면 이 필터를 안탐.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    /**
     * 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 됨.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(">>> 인증이나 권한이 필요한 주소가 요청됨.");

        /** header 값 확인 */
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("jwtHeader : " + jwtHeader);

        /** JWT 토큰을 검증하여 정상적인 사용자인지 확인 */
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace("Bearer ", "");

        String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET_KEY)).build().verify(token).getClaim("username").asString();
        /** 서명이 정상적으로 됨 */
        if (username != null) {
            User userEntity = userRepository.findByUsername(username);
            System.out.println("userRole : " + userEntity.getRoleList());

            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            // JWT 토큰 서명을 통해 인증이 된 사용자이므로 Authentication 객체를 직접 만들어주어도 됨.
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            /** 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장 */
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }


    }
}

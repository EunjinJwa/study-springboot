package springboot.security.jwtserver.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터2");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        /**
         * id, pw 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고 그걸 응답해준다.
         * 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 옴.
         * 그때 토큰이 내가 만든 토큰이 맞는지만 검증하면 됨. (RSA, HS256)
         */
        if (req.getMethod().equals("POST")) {
            System.out.println("POST 요청");
            String headerAuth = req.getHeader("Authorization");
            System.out.println("headerAuth : " + headerAuth);

            if (headerAuth.equals("hello")) {
                filterChain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증 안됨");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}

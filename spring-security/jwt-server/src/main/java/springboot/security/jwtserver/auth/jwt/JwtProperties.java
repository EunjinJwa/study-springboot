package springboot.security.jwtserver.auth.jwt;

public interface JwtProperties {

    String SECRET_KEY = "jin";
    int EXPIRATION_TIME = 60000 * 10;   // 10분
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}

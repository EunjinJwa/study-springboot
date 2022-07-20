package springboot.security.jwtserver;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import springboot.security.jwtserver.auth.jwt.JwtProperties;

import java.util.Base64;

public class JwtTokenTest {

    @Test
    public void parsingJwtToken() {

//        final String secretKey = "31328f87da5d46ed99fcc4617f3925c0";
//        final String basicKey = "dHJ1c3RlZC1kYS1zZXJ2ZXI6MzEzMjhmODdkYTVkNDZlZDk5ZmNjNDYxN2YzOTI1YzA=";
//
//         final String clientId = "trusted-da-server";
//
//
//        String encodingKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
////        String encodingBasicKey = Base64.getEncoder().encodeToString(basicKey.getBytes());
//        String encodingClientId = Base64.getEncoder().encodeToString(clientId.getBytes());

        // 만료일 확인
        final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCJdLCJleHAiOjE2NjI3ODE5ODcsImF1dGhvcml0aWVzIjpbIlJPTEVfVFJVU1RFRF9TRVJWRVIiXSwianRpIjoiM2I2MDI5YzQtNjA2Ny00Y2E5LWFlNmQtY2EyOTQyMWRiMTIyIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1kYS1zZXJ2ZXIifQ.8kv_5RvJNjwhK2_EGt43thl36RBw_lVWzSBO62g-ipw";
        DecodedJWT djwt = JWT.decode(token);
        System.out.println(djwt.getAlgorithm());
        System.out.println(djwt.getExpiresAt());
        System.out.println(djwt.getPayload());
        System.out.println(djwt.getHeader());
    }

    @Test
    public void parsingJwtToken2() {
        final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ0cnVzdCJdLCJleHAiOjE2NjI3ODE5ODcsImF1dGhvcml0aWVzIjpbIlJPTEVfVFJVU1RFRF9TRVJWRVIiXSwianRpIjoiM2I2MDI5YzQtNjA2Ny00Y2E5LWFlNmQtY2EyOTQyMWRiMTIyIiwiY2xpZW50X2lkIjoidHJ1c3RlZC1kYS1zZXJ2ZXIifQ.8kv_5RvJNjwhK2_EGt43thl36RBw_lVWzSBO62g-ipw";
        final String secretKey = "31328f87da5d46ed99fcc4617f3925c0";
        final String basicKey = "dHJ1c3RlZC1kYS1zZXJ2ZXI6MzEzMjhmODdkYTVkNDZlZDk5ZmNjNDYxN2YzOTI1YzA=";
        final String clientId = "trusted-da-server";

        String encodingKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        String encodingBasicKey = Base64.getEncoder().encodeToString(basicKey.getBytes());
        String encodingClientId = Base64.getEncoder().encodeToString(clientId.getBytes());

        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

        System.out.println(claims);
        System.out.println(claims.getBody());
        System.out.println(claims.getBody().getExpiration());

    }
}

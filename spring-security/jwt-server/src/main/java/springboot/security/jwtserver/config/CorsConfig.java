package springboot.security.jwtserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configration = new CorsConfiguration();
        configration.setAllowCredentials(true);     // 내 서버가 응답할때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것.
        configration.addAllowedOrigin("*");         // 모든 ip에 응답 허용
        configration.addAllowedHeader("*");         // 모든 header에 응답 허용
        configration.addAllowedMethod("*");         // 모든 post, get, put, delete, patch 요청을 허용

        source.registerCorsConfiguration("/api/**", configration);
        return new CorsFilter(source);
    }
}

package springboot.security.jwtserver.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.security.jwtserver.filter.MyFilter1;
import springboot.security.jwtserver.filter.MyFilter2;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);   // 낮은 번호가 필터중에서 가장 먼저 실행됨.
        return bean;
    }
//
//    @Bean
//    public FilterRegistrationBean<MyFilter2> filter2() {
//        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(1);   // filter 우선순위 2
//        return bean;
//    }

}

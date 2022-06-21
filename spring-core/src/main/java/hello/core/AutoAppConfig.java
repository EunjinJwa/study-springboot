package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // Test용으로 작성한 AppCinfig.class 를 ComponentScan으로 자동 등록되지 않기 위해 Filter로 등록함.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)     //
)
public class AutoAppConfig {

}

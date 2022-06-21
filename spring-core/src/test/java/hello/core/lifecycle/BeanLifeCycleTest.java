package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        NetworkClient2 networkClient = ac.getBean(NetworkClient2.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient2 networkClient() {
            NetworkClient2 networkClient2 = new NetworkClient2();
            networkClient2.setUrl("http://hello-spring.dev");
            return networkClient2;
        }
    }
}

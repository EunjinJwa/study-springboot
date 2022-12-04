package jinny.study.springboot.springpractice.dao;

import jinny.study.springboot.springpractice.config.AConnectionMaker;
import jinny.study.springboot.springpractice.config.ConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(getConnectionMaker());
    }

    private ConnectionMaker getConnectionMaker() {
        return new AConnectionMaker();
    }

}

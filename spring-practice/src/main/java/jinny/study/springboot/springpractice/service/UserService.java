package jinny.study.springboot.springpractice.service;

import jinny.study.springboot.springpractice.dao.UserDao;
import jinny.study.springboot.springpractice.domain.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserService {

    public User getUser(String userId) throws SQLException {

        ApplicationContext ap = new AnnotationConfigApplicationContext();
        UserDao userDao = ap.getBean("userDao", UserDao.class);

        return userDao.get(userId);
    }

    public void addUser(User user) {
        ApplicationContext ap = new AnnotationConfigApplicationContext();
        UserDao userDao = ap.getBean("userDao", UserDao.class);
        userDao.add(user);
    }

}

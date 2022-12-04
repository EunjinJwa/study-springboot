package jinny.study.springboot.springpractice.dao;

import jinny.study.springboot.springpractice.config.ConnectionMaker;
import jinny.study.springboot.springpractice.domain.entity.User;

import java.sql.SQLException;

public class UserDao {

    ConnectionMaker cnn;

    public UserDao(ConnectionMaker cnn) {
        this.cnn = cnn;
    }

    public User get(String userId) throws SQLException {
        cnn.getConnection().prepareStatement("select ...");
        return User.builder().name("kassy").build();
    }

    public void add(User user) {

    }


}

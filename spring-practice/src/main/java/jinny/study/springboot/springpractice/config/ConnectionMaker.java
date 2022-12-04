package jinny.study.springboot.springpractice.config;

import java.sql.Connection;

public interface ConnectionMaker {

    Connection getConnection();

}

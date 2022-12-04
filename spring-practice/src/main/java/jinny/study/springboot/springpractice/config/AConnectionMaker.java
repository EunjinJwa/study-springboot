package jinny.study.springboot.springpractice.config;

import java.sql.Connection;

public class AConnectionMaker implements ConnectionMaker{
    @Override
    public Connection getConnection() {
        return null;
    }
}

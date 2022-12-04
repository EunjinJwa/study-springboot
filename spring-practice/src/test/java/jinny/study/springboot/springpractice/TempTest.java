package jinny.study.springboot.springpractice;

import jinny.study.springboot.springpractice.domain.entity.User;
import org.junit.jupiter.api.Test;

public class TempTest {

    @Test
    public void UserTest() {
        User user = User.builder().name("kassy").build();
        user.setPassword("123");

        System.out.println(user);

    }

}

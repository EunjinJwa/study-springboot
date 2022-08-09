package jinny.study.springboot.awswebservice.config.auth;

import jinny.study.springboot.awswebservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보를 세션에 넣어두고 사용하기 위해 사용함
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

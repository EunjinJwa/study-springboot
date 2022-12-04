package jinny.study.springboot.springpractice.domain.entity;

import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter
@ToString
public class User {
    @NotNull
    String name;
    String password;
    String email;
}

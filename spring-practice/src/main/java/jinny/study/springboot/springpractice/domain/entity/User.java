package jinny.study.springboot.springpractice.domain.entity;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@ToString
public class User {
    @NotNull
    String name;
    String password;
    String email;
}

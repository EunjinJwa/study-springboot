package jinny.study.springboot.awswebservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_QUEST", "손님"),      // Spring Security에서 권한 코드는 꼭 'ROLE_' 로 시작해야함.
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}

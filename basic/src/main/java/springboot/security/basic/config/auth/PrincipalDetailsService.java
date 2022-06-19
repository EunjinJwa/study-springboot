package springboot.security.basic.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.security.basic.model.User;
import springboot.security.basic.repository.UserRepository;

// 시큐리티 설정(SecurityConfig.java)에서 loginProcessingUrl("/login");
// 로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 메서드가 실행이 된다.
// 그러므로 실제로 수행할 로직을 loadUserByUsername 메서드에 구현해주어야 한다.

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 시큐리티 session => Authentication => UserDetails
     * loadUserByUsername() 메서드의 리턴된 값(UserDetails)이 시큐리티 session(내부 Authentication(내부 UserDetails)) 이렇게 들어감
     * 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        System.out.println("userEntity : " + userEntity.getRole());
        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}

package springboot.security.jwtserver.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.security.jwtserver.model.User;
import springboot.security.jwtserver.repository.UserRepository;


/**
 * http://localhost:8081/login
 */
@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> PrincipalDetailService 의 loadUserByUsername()");
        User userEntity = userRepository.findByUsername(username);
        return new PrincipalDetails(userEntity);
    }

}

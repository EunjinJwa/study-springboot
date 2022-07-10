package springboot.security.jwtserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.security.jwtserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

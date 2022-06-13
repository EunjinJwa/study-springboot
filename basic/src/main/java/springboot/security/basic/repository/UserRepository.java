package springboot.security.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.security.basic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}

package jinny.study.hellospringboot.repository;

import jinny.study.hellospringboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository를 extends하는 Interface만 만들어 놓으면,
 * Spring Data Jpa가 구현체를 만들어서 스프링빈에 등록을 한다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // * Method 규칙 *
    // [findBy + 컬럼명] 으로 작성해주면 아래와 같은 쿼리와 같게 만들어줌
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}

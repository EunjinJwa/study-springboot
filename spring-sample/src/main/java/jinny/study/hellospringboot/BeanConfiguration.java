package jinny.study.hellospringboot;

import dev.jinny.java.study.hellospringboot.repository.*;
import jinny.study.hellospringboot.repository.MemberRepository;
import jinny.study.hellospringboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

//    private DataSource dataSource;
//
//    @Autowired
//    public BeanConfiguration(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }



//    @PersistenceContext
//    private EntityManager em;
//


    /**
     * Spring Data JPA
     */
    private final MemberRepository memberRepository;

    @Autowired
    public BeanConfiguration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 빈 직접 생성 방법
     * @Bean 어노테이션으로 생성된 메서드를 Spring 컨테이너에 Bean으로 등록해준.
      */
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }



//    @Bean
//    public MemberRepository memberRepository() {
//
//        /**
//         * 인터페이스에서 구현체 변경을 쉽게 할 수 있다.
//         */
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//
//    }








}

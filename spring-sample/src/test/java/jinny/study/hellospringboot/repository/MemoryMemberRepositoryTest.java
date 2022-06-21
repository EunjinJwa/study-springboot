package jinny.study.hellospringboot.repository;

import jinny.study.hellospringboot.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * MemoryMemberRepository 에서 store 를 공유하므로, 아래 TEST 코드 실행시 서로 간에 의존이 생겨 오류가 발생할 수 있음.
     * 따라서 해당 resource를 clear 해주는 작업이 필요하다.
     * @AfterEach : @Test 메서드 하나 실행할 때마다 실행 후 요청될 수 았는 어노테이션
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        // 검증 방법 #1
        System.out.println("result = " + (result == member));

        // 검증 방법 #2
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertEquals(result, member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertEquals(result.size(), 2);
    }

}
package jinny.study.hellospringboot.service;

import jinny.study.hellospringboot.domain.Member;
import jinny.study.hellospringboot.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 스프링 컨테이너와 DB연동까지 확인하는 통합 테스트
 */

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional  // 중요 !! 테스트 케이스에 @Tranjactional 어노테이션이 있으면 Test가 끝난 후 rollback 처리함. TEST용 데이터를 안남길 수 있음.
public class MemberServiceIntegrationTest {

    @Autowired
    private MemberService memberService;

    @Autowired private MemberRepository memberRepository;

    @Test
//    @Commit  // @Transactional 이 있더라도 commit 처리를 해줌.
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("kassy5");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /**
     * Exception 검증 방법
     * 시나리오 : 중복된 회원명으로 join시 IllegalStateException 이 발생해야 함.
     */
    @Test
    public void 중복Exception() {
        //given
        Member member1 = new Member();
        member1.setName("duplicatedTest");

        Member member2 = new Member();
        member2.setName("duplicatedTest");

        //when
        memberService.join(member1);

        // 검증방법 #1
        // try/catch 사용하여 다소 코드가 길어짐
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        // 검증방법 #2
        // 2.1 Exception 검증 : 비즈니스 로직 실행시 지정한 Exception이 발생하는지 체크함.
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 2.2 Exception Message 검증
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}

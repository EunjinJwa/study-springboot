package jinny.study.hellospringboot.service;

import jinny.study.hellospringboot.domain.Member;
import jinny.study.hellospringboot.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 순수하게 java를 사용한 단위 테스트
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

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
    public void duplicatedJoinException() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
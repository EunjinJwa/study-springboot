package jinny.study.hellospringboot.service;

import jinny.study.hellospringboot.domain.Member;
import jinny.study.hellospringboot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service는 비즈니스에 의존적으로 설계를 한다.
 * => 메서드 이름도 비즈니스적인 이름으로 작성함.
 */

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // DI(Dependency Injection) : 외부에서 주입함
    // 생성자를 사용하는 방법
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    


    /**
     * 회원가입
     */
    public Long join(Member member) {

        validationDuplicateMember(member);  // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 회원 이름 중복 조회
     * @param member
     */
    private void validationDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {                               // ifPresent : Optional의 함수
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회 by memberId
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

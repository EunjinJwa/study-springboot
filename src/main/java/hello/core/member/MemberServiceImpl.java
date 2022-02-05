package hello.core.member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 인터페이스의 구현체를 지정해줌. (지정안해줄수가 없음.)
    // 추상화와 구체화를 모두 의존하고 있으므로 DIP를 위반하고 있음.

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

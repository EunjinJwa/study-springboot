package hello.core.member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    /**
     * 생성자 주입
     * memberRepository 의 구현체를 MemberServiceImpl에서 직접 정의하지 않고, 생성자를 통해 주입받음
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

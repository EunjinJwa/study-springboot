package hello.core.member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}

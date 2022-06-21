package hello.core.member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}

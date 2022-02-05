package hello.core.discount;

import hello.core.member.Member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public interface DiscountPolicy {

    /**
     * @param member
     * @param price
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);

}

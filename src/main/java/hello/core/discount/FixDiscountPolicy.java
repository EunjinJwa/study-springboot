package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * Created by Eunjin on 2022-02-06.
 */
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}

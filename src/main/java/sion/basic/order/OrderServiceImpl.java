package sion.basic.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sion.basic.annotation.MainDiscountPolicy;
import sion.basic.discount.DiscountPolicy;
import sion.basic.discount.FixDiscountPolicy;
import sion.basic.member.Member;
import sion.basic.member.MemberRepository;
import sion.basic.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    // Test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

package sion.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sion.basic.discount.DiscountPolicy;
import sion.basic.discount.RateDiscountPolicy;
import sion.basic.member.MemberService;
import sion.basic.member.MemberServiceImpl;
import sion.basic.member.MemoryMemberRepository;
import sion.basic.order.OrderService;
import sion.basic.order.OrderServiceImpl;

@Configuration
public class AppCofing {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}

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

    // 실행 순서 예상
    // call AppCofing.memberService
    // call AppCofing.memberRepository
    // call AppCofing.orderService
    // call AppCofing.memberRepository
    // call AppCofing.memberRepository

    // 실제
    // call AppCofing.memberService
    // call AppCofing.memberRepository
    // call AppCofing.orderService
    @Bean
    public MemberService memberService() {
        System.out.println("call AppCofing.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppCofing.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppCofing.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}

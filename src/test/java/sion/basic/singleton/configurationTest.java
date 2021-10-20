package sion.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sion.basic.AppCofing;
import sion.basic.member.MemberRepository;
import sion.basic.member.MemberService;
import sion.basic.member.MemberServiceImpl;
import sion.basic.order.OrderService;
import sion.basic.order.OrderServiceImpl;

public class configurationTest {
    @Test
    @DisplayName("싱글톤 생성시 객체는 여러번 호출인데 다 같은 객체인가?")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppCofing.class);

        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = orderService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
        Assertions.assertThat(memberRepository2).isSameAs(memberRepository);
    }
}

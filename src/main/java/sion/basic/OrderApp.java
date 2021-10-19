package sion.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sion.basic.member.Grade;
import sion.basic.member.Member;
import sion.basic.member.MemberService;
import sion.basic.member.MemberServiceImpl;
import sion.basic.order.Order;
import sion.basic.order.OrderService;
import sion.basic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppCofing.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        Long memberId = 1L;
        Member member = new Member(memberId, "sion", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order =" + order);
    }
}

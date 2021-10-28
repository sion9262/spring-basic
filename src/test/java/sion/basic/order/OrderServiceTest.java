package sion.basic.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sion.basic.AppCofing;
import sion.basic.member.Grade;
import sion.basic.member.Member;
import sion.basic.member.MemberService;
import sion.basic.member.MemberServiceImpl;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppCofing appCofing = new AppCofing();
        memberService = appCofing.memberService();
        orderService = appCofing.orderService();
    }
    @Test
    void createOrderTest() {

        // given
        Long memberId = 1L;
        // given
        Member member = new Member(1L, "sion", Grade.VIP);

        // when
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}

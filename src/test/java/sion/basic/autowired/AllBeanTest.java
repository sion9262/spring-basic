package sion.basic.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sion.basic.AutoAppConfig;
import sion.basic.discount.DiscountPolicy;
import sion.basic.member.Grade;
import sion.basic.member.Member;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "AII", Grade.VIP);

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        int fixPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        int ratePrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(fixPrice).isEqualTo(1000);
        Assertions.assertThat(ratePrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            return policyMap.get(discountCode).discount(member, price);
        }
    }
}

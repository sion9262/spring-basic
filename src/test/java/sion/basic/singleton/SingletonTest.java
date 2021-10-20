package sion.basic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sion.basic.AppCofing;
import sion.basic.member.MemberService;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppCofing appCofing = new AppCofing();

        //1. 조회 : 호출할 때 마다 객체를 생성;
        MemberService memberService = appCofing.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성;
        MemberService memberService2 = appCofing.memberService();

        System.out.println("memberService 1" + memberService);
        System.out.println("memberService 2" + memberService2);

        // memberService != memberService2
        Assertions.assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void singletonServiceTest() {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService).isSameAs( singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // AppCofing appCofing = new AppCofing();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppCofing.class);

        //1. 조회 : 호출할 때 마다 객체를 생성;
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        //2. 조회 : 호출할 때 마다 객체를 생성;
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService 1" + memberService);
        System.out.println("memberService 2" + memberService2);

        // memberService != memberService2
        Assertions.assertThat(memberService).isSameAs(memberService2);

    }
}

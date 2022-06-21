package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
//        SingletoneService 의 생성자가 private이므로 new 키워드로 객체 생성 불가!
//        SingletoneService singletoneService1 = new SingletoneService();

        SingletoneService singletoneService1 = SingletoneService.getInstance();
        SingletoneService singletoneService2 = SingletoneService.getInstance();

        System.out.println("memberService1 = " + singletoneService1);
        System.out.println("memberService2 = " + singletoneService2);

        Assertions.assertThat(singletoneService1).isSameAs(singletoneService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 조회: SpringContainer에 생성된 빈 인스턴스를 조회함.
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    void appConfigClassTest() {
        // 빈이 아닌, 직접 생성한 AppConfig
        AppConfig appConfig1 = new AppConfig();

        // 스프링 컨테이너에 빈으로 등록된 AppConfig
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig2 = ac.getBean(AppConfig.class);

        System.out.println(appConfig1.getClass());  // class hello.core.AppConfig
        System.out.println(appConfig2.getClass());  // class hello.core.AppConfig$$EnhancerBySpringCGLIB$$c1308b36
    }
}

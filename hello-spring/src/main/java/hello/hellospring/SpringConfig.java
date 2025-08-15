package hello.hellospring;
import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository; //그냥 이렇게 변수 등록하고

    //MemberRepository를 스프링컨테이너에서 찾아야되는데 등록된게 없잖아?
    //근데 SpringDataMemberRepository에서 JPARepository를 상속받으면 스프링데이터JPA가 알아서 구현체를 만들어준다는데?
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //만들어 놓으면 됌, AutoWired는 생성자가 하나니까 생략
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource); //데이터소스는 스프링이 제공해준다.
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em); //EntiryManager 주입
//    }

//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
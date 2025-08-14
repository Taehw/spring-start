package hello.hellospring;
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

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    } //데이터 소  스 주입

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource); //데이터소스는 스프링이 제공해준다.
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em); //EntiryManager 주입
    }
}
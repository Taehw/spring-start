package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //각 테스트가 끝나고 동작
    public void afterEach() {
        repository.clearStore();
    }

    @Test //아래 테스트 메서드가 실행가능하게 해주는 어노테이션
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();//사실 get으로 꺼내는게 좋은방법은 아님 테스트코드니까 그냥 ㄱㄱ

        //방법 1 -> 이렇게 해도 되지만 매번 출력결과를 확인할순 없음
        //System.out.println("result = " + (result == member));

        //방법 2 -> Expected value(왼쪽), Actual value(오른쪽) result가 null이면 test 통과x
        //Assertions.assertEquals(member, result);

        //방법3 -> 방법2와 사용하는 Assertions 의 라이브러리가 다름
        assertThat(member).isEqualTo(result); //alt+enter -> staic import를 통해 앞에 Assertions 생략가능
    }

    @Test
    public void findByName() {

        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+F6으로 한번에 바꿔주기
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get(); //get 으로 꺼내면 Optional로 감싸지 않아도 됨

        //then -> 위의 result들 spring2로 바꾸면 빨간불 -> other object
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2); //간단하게 검사
    }
}

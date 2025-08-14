package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 부트 만세
@Transactional
class MemberServiceIntegrationTest {

    //이제는 스프링 컨테이너 앞에 멤버 서비스와 리포지토리를 줘야함
    //테스트코드는 젤끝단이기 때문에 편한방법 쓰면댐 -> AutoWired만 써주면 됌, BeforeAfter Each 필요없음
    @Autowired MemberService memberService ;
    @Autowired MemberRepository memberRepository;

    @Test
//    @Commit -> 해당 어노테이션 있으면 반영됌
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("spring");
        //when -- memberService에 join을 검정
        //우린 스펙상 저장한 아이디로 반환되기 때문에 Long saveId사용
        Long saveId = memberService.join(member);

        //then
        //우리가 저장한게 리포지토리에 있는지 찾고싶음
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    } //-> 예제가 너무 단순, 정상플로우도 중요하지만 예외플로우에 대한 검증 필요 -> 회원 중복 가입

    //중복회원검증 로직을 검사해보자
    @Test
    public void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

}
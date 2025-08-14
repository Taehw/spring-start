package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService ;//= new MemberService();
    //테스트는 과감하게 한글로 바꿔도 됨
    //실제 Production으로 나가는 코드들은 한글로 적기 애매ㅏㅁ
    //Test는 영어권사람들이랑 인사하는거 아니면 굳이임
    MemoryMemberRepository memberRepository; //= new MemoryMemberRepository();
    //store 를 클리어해줘야하기 때문에 레포지토리를 생성

    @BeforeEach
    //Ctrl 눌러서 타고 들어가보기
    //DI - Dependency Injection
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach //각 테스트가 끝나고 동작
    public void afterEach() {

        memberRepository.clearStore();
    }



    @Test //모든 테스트가 given, when, then으로 대부분 짤림 -> 코드 파악에 용이 //추후 필요시 구조는 변경할것
    void 회원가입() {

        //given
        Member member = new Member();
        member.setName("hello");
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
//        //방법1 -> try catch
//        try {
//            memberService.join(member2);
//            fail(); //여기까지 오면 일단 fail
//        } catch (IllegalStateException e) { //예외가 터져서 정상적으로 서공
//            assertEquals("이미 존재하는 회원입니다.", e.getMessage());
//        }
//
//        //then - 터지는 예외 잡기 - try-catch 로 잦는것도 가능

        //방법2 - assertThrows
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        //() -> memberService.join(member2) 을 실행할건데 IllegalState 예외가 발생해야한다.
        assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
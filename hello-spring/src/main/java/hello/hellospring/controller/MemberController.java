package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //컨트롤러가 서비스를 가져다 쓰기 위해 memberService 객체 생성

    //방법1 : new 로 생성 -> 문제 : 하나만 생성해서 공용으로 쓰고 싶은데 여러개의 인스턴스가 생성됨.
    //private final MemberService memberService = new MemberService();

    //방법2 : spring container에 등록-> 하나만 생성됨( 방법1의 개선안)
    private final MemberService memberService;

    //방법2. 생성자로 연결
    //@Autowired //-> 아래 생성자의 매개변수가 스프링의 컨테이너와 연결됨
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

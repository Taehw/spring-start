package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //회원서비스를 위해선 회원레포지토리가 있어야함
    private final MemberRepository memberRepository;

    //다른 객체를 쓰지않고 같은 객체를 쓰기 위해서 in test
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //memberRepository를 내부에서 넣어주는게 아니라 외부에서 넣어주도록

    /**
     * 회원가입
     */
    public Long join(Member member) {

//        //중복 회원 검증
//        //Ctrl + Alt + V 로 편하게 변수 생성
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> { //member에 값이 있으
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); //result가 optional이기 때문에 가능함. 아니라면 ifNull 같은걸로 코딩해야함
//        //지금은 null의 가능성이 보인다면 Optional로 감싸서 반환하햇 ifPresent같을 사용
//        //orElseGet같은것도 많이 이용

        //Optional 생략 버전 -> optional을 바로 반환하게는 좋지 않음 왜????
//        memberRepository.findByName(member.getName())
//                        .ifPresent(m-> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });

        //Ctrl+M으로 메서드 추출

        validateDuplicateMember(member); //중복회원검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

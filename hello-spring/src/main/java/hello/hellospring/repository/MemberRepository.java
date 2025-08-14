package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //1. 회원 저장
    Member save(Member member);

    //2. 회원 찾기
    Optional<Member> findById(Long id);
    //Optional -> Null을 처리할때 감싸주기 위한 도구
    Optional<Member> findByName(String name);

    //3. 모든 회원 찾기
    List<Member> findAll();

//    //4.
//    void clearStore();
}
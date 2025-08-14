package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //interface는 다중 상속 가능하기때문에 두개 들어감
    //JpaRepository의 경우 <T, Id> 우리는 Member 객체와 PK로 id를 쓰기때문에 각각의 맞는 타입을 넣어줌


    //select m from Member m where m.name = ? -- JPQL
    //일정한 규칙을 가지고 쿼리를 잘 짜준다 -> findBy의 규칙성
    //인터페이스만으로 끝내버리기
    @Override
    Optional<Member> findByName(String name);
}

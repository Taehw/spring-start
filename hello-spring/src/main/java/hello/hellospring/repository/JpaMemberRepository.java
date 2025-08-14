package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository  implements MemberRepository{

    private final EntityManager em;
    //JPA는 EntityManager 로 모든걸 동작시킴
    //build.gradle에서 data-jpa를 받았으면 스프링부트가 알아서 EntityManager를 생성시켜줌
    //그래서 만들어진 EntityManager를 injection 받기만 하면 된다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist 영구히 추가한다.
        return member;
    } //-> 이렇게만 하면 insert알아서 되고 id도 알아서 생성됨 ㄷㄷ

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회하는건 이렇게 식별자 pk 값까지 넣어주기
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //특별한 JPQL이라는 객체지향쿼리를 사용해야함 - 객체를 대상으로 쿼리를 날리면 SQL로 번역이됨
        //JPQL 조차도 스프링 data JPA 와 함께라면 짤필요 없음
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    //inline 단축키로 합쳐주기
}

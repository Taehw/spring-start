package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //원래는 동시성 문제를 고려해서 ConcurrentHashMap이나 AtomicLong 사용을 고려해야함 (공유되는 변수의 경우)
    private static long sequence = 0L; //key값 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        //member가 넘어올때 id와 name이 같이 넘어옴
        store.put(member.getId(), member);

        return member;
    }

    @Override
    //결과가 없는경우 어떻게 처리하는지에 대해 생각해야함
    //Null이 반환될 경우가 있으면 Optional로 감쌈
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //자바8의 lamda를 사용
        //member의 getName이 파라미터로 넘어온 name과 같은지 확인
        //loop를 돌면서
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //findAny() -> 하나라도 찾아라 (default로 optional 반환)
        //loop를 돌면서 하나 찾아지면 반환해버림
        //끝까지 돌렸는데 없으면 Null값이 opitonal에 감싸져서 반환
    }

    @Override
    public List<Member> findAll() {
        //Map이지만 반환은 List
        return new ArrayList<>(store.values()); //store의 value는 member member들의 List가 반환됨
    }

    @Override
    public void clearStore() {
        store.clear(); //저장된 목록 쏵삭제
    }
}

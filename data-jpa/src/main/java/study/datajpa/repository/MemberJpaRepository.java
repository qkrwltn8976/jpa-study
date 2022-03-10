package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext // 스프링부트가 스프링 컨테이너와 jpa에 있는 영속성 컨텍스트 entity manager를 가져옴
    // jpa가 알아서 쿼리를 날려 db에서 동작
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }

    // 전체를 조회하거나 특정 필터링이 필요한 경우 JPA의 JPQL 사용
    // 객체를 대상으로 하는 쿼리
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // Java8부터 Optional<T>클래스를 사용해 NullPointerException(이하 NPE)를 방지
    public Optional<Member> findNById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member); // member가 null일수도 있음
    }

    public long count() {
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult(); // 단건조회
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

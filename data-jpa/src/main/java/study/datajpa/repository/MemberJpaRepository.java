package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberJpaRepository {

    @PersistenceContext // 스프링부트가 스프링 컨테이너와 jpa에 있는 영속성 컨텍스트 entity manager를 가져옴
    // jpa가 알아서 쿼리를 날려 db에서 동작
    private EntityManager em;

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

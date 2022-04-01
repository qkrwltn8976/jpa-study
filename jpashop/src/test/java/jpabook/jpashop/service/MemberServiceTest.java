package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class) // junit 실행 시 스프링과 엮어 실행
@SpringBootTest // 스프링부트를 띄운 상태에서 테스트하고 싶을 때 사용
@Transactional // 테스트 끝나면 롤백
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(false) // 롤백하지 않고 커밋하여 insert 쿼리 볼 수 있음, 없으면 transactional 어노테이션으로 인해 롤백됨
    public void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        em.flush(); // db에 쿼리 실행하여 insert문 볼 수 있음
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void validateDuplicateMember() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        // when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야 함

        // then
        Assert.fail("예외가ㅏ 바라생해야 한다.");
    }
}
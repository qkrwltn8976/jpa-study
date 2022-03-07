package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter // 생성자를 통해 객체 생성하는 것이 각각을 set하는 것보다 더 좋은 방법임
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String username;

    // JPA 표준 스펙에 entity는 기본적으로 파라미터 없는 디폴트 생성자 필요
    // protected: JPA가 프록싱 기술을 사용할 경우 JPA 구현체들이 객체를 강제로 생성 시 private으로 설정하면 사용불가
    protected Member() {}

    public Member(String username) {
        this.username = username;
    }
}

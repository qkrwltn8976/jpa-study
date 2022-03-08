package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter // 생성자를 통해 객체 생성하는 것이 각각을 set하는 것보다 더 좋은 방법임
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩으로 설정해야 성능 최적화 가능, 가짜 객체로 설정해두고 실제 값을 사용할 때 조회
    @JoinColumn(name = "team_id")
    private Team team;

    // JPA 표준 스펙에 entity는 기본적으로 파라미터 없는 디폴트 생성자 필요
    // protected: JPA가 프록싱 기술을 사용할 경우 JPA 구현체들이 객체를 강제로 생성 시 private으로 설정하면 사용불가
//    protected Member() {}

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}

package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // foreign key가 없는 엔티티에 mappedBy 설정해줌
    private List<Member> members = new ArrayList<>();

    // 생성자 단축키 : command + n
    public Team(String name) {
        this.name = name;
    }
}

package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장 타입을 사용했다 (embedded, embedable 중 하나만 있으면 됨)
    private Address address;

    @OneToMany(mappedBy = "member") // 연관관계의 주인이 아닌 entity에 mapped by 적어줌 -> 읽기전용
    private List<Order> orders = new ArrayList<>();

}

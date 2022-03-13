package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private long id;

    // order가 연관관계의 주인
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne // 자주 사용하는 테이블에 FK 설정, 연관관계의 주인
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // java 8에서는 localdatetime 사용할 시 hibernate에서 자동으로 해줌
    private LocalDateTime orderDate;

    private OrderStatus status; // 주문상태 (ORDER, CANCEL)
}

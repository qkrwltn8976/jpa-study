package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장 값 타입
@Getter // 변경이 불가능하도록 setter를 만들지 않음
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // JPA 스펙 상 기본 생성자 필요, 사용하지 못하게 protected로 설정
    }

    // 생성자 단축키 cmd + n
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

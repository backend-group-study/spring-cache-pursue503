package com.study.spring.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;

    private String name;

    private int age;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .age(this.age)
                .build();
    }

}

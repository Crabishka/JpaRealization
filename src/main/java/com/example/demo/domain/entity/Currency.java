package com.example.demo.domain.entity;


import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "id")
    private Long id;

    private Long resourceId;

    private String name;

    private Long count;

    public Currency() {

    }

    public Currency(Long id, Long resourceId, String name, Long count) {
        this.id = id;
        this.resourceId = resourceId;
        this.name = name;
        this.count = count;
    }
}

package com.example.demo.domain.entity;


import javax.persistence.*;

@Entity

@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")

    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "count")
    private Long count;

    @Column(name = "level")
    private Long level;
}

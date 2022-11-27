package com.example.demo.domain.entity;



import javax.persistence.*;

@Entity

@Table(name = "progress")
public class Progress {
    @Id
    @Column(name = "id")

    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "score")
    private Long score;

    @Column(name = "max_score")
    private Long maxScore;

    @ManyToOne
    private Player player;


}

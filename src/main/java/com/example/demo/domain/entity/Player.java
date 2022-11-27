package com.example.demo.domain.entity;



import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "player")
public class Player {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "currency_id"))
    private List<Currency> currencies;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @OneToMany(cascade = {CascadeType.MERGE})
    @JoinTable(joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "progress_id"))
    private List<Progress> progresses;


}

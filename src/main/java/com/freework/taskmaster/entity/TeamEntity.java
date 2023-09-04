package com.freework.taskmaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.Set;

@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity admin;

    @ManyToMany
    @JoinTable(name = "team_user"
        ,joinColumns = @JoinColumn(name="team_id")
        ,inverseJoinColumns = @JoinColumn(name="user_id"))
    private Set<UserEntity> participants;
}

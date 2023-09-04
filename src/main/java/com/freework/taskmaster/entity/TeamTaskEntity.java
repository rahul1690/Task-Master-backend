package com.freework.taskmaster.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("team")
public class TeamTaskEntity extends TaskEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assigned_team_id")
    private TeamEntity assignedTo;
}

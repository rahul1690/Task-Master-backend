package com.freework.taskmaster.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("single")
public class UserTaskEntity extends TaskEntity{

    @ManyToOne
    @JoinColumn(name = "assigned_user_id")
    private UserEntity assignedTo;

}

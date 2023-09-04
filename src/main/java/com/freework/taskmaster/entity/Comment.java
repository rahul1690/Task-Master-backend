package com.freework.taskmaster.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commented_user_id")
    private UserEntity commentedBy;

    private LocalDateTime commentedAt;

    @Column(length = 1000)
    private String comment;

    private String attachment;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskEntity task;



}

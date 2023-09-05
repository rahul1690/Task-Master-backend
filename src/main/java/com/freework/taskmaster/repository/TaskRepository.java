package com.freework.taskmaster.repository;

import com.freework.taskmaster.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

}

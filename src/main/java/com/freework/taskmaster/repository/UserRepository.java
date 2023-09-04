package com.freework.taskmaster.repository;

import com.freework.taskmaster.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}

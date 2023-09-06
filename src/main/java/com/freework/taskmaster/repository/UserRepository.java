package com.freework.taskmaster.repository;

import com.freework.taskmaster.entity.UserEntity;
import com.freework.taskmaster.model.UserModel;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    Set<UserModel> findByAdmin(UserEntity admin);
}

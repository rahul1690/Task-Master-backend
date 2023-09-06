package com.freework.taskmaster.repository;

import com.freework.taskmaster.entity.TeamEntity;
import com.freework.taskmaster.entity.UserEntity;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity,Long> {

    Optional<TeamEntity> findByTeamName(String teamName);
    Set<TeamEntity> findByAdmin(UserEntity admin);
}

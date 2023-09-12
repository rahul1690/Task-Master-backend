package com.freework.taskmaster.serviceImpl;

import static com.freework.taskmaster.service.ResponseMessages.TEAM_ALREADY_EXIST;
import static com.freework.taskmaster.service.ResponseMessages.USER_NOT_FOUND;

import com.freework.taskmaster.entity.TeamEntity;
import com.freework.taskmaster.entity.UserEntity;
import com.freework.taskmaster.exceptions.TeamAlreadyExistException;
import com.freework.taskmaster.exceptions.UserNotFoundException;
import com.freework.taskmaster.model.TeamModel;
import com.freework.taskmaster.repository.TeamRepository;
import com.freework.taskmaster.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TeamServiceImpl {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    public TeamModel createTeam(TeamModel teamModel,String username){
        UserEntity admin = userRepository.findByUsername(username).get();
        if(teamRepository.findByTeamName(teamModel.getTeamName()).isPresent()){
            throw new TeamAlreadyExistException(String.format(TEAM_ALREADY_EXIST,teamModel.getTeamName()));
        }
        TeamEntity teamEntity = modelMapper.map(teamModel,TeamEntity.class);
        teamEntity.setAdmin(admin);
        log.info("Team Entity ->{}",teamEntity);
        return modelMapper.map(teamRepository.save(teamEntity), TeamModel.class);
    }


    public Set<TeamModel> findAllTeamsByAdmin(String username){
        UserEntity admin = userRepository.findByUsername(username)
            .orElseThrow(()->new UserNotFoundException(USER_NOT_FOUND));
        Set<TeamEntity> teams = teamRepository.findByAdmin(admin);
        return teams.stream().map(teamModel->modelMapper.map(teamModel, TeamModel.class)).collect(
            Collectors.toSet());
    }
}

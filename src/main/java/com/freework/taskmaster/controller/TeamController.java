package com.freework.taskmaster.controller;

import static com.freework.taskmaster.service.ResponseMessages.TEAM_CREATED;

import com.freework.taskmaster.model.Response;
import com.freework.taskmaster.model.TeamModel;
import com.freework.taskmaster.serviceImpl.TeamServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping("/create-team")
    public Response<?> createTeam(@Valid @RequestBody TeamModel teamModel,@RequestParam String username){
        return Response.ok().setPayload(teamService.createTeam(teamModel,username)).setResponse(Response.responseMessage("success",TEAM_CREATED));
    }

    @GetMapping("/get-teams")
    public Response<?> getAllTeams(@RequestParam String username){
        return Response.ok().setPayload(teamService.findAllTeamsByAdmin(username));
    }


}

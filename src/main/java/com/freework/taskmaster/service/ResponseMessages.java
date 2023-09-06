package com.freework.taskmaster.service;

public interface ResponseMessages {

    String ADD_USER_SUCCESS="User Registered Successfully.";
    String USER_ALREADY_EXIST="User with username %s already exist.";
    String INVALID_USERNAME="Please Enter a Valid Username";
    String INVALID_PASSWORD="Please Enter a Valid Password";
    String EMPTY_ROLE="Role Should Not Be Empty";
    String TEAM_ALREADY_EXIST = "Team with Team Name %s already exist";
    String TEAM_CREATED = "Team Created Successfully";
    String USER_NOT_FOUND = "User Not Found";
    String TEAMS_NOT_FOUND = "Teams Not Found";
    String DELETED_USER = "User with User ID %d Deleted Successfully";
}

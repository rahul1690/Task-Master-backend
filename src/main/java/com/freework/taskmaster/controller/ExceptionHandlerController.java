package com.freework.taskmaster.controller;

import com.freework.taskmaster.exceptions.EmptyRoleException;
import com.freework.taskmaster.exceptions.TeamAlreadyExistException;
import com.freework.taskmaster.exceptions.TeamsNotFoundException;
import com.freework.taskmaster.exceptions.UserNotFoundException;
import com.freework.taskmaster.exceptions.UsernameAlreadyExistsException;
import com.freework.taskmaster.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Response  userNotFound(UserNotFoundException userNotFoundException){
        userNotFoundException.printStackTrace();
        return Response.notFound().setResponse(Response.responseMessage("error",userNotFoundException.getMessage()));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response userAlreadyExist(UsernameAlreadyExistsException usernameAlreadyExistsException){
        usernameAlreadyExistsException.printStackTrace();
        return Response.badRequest().setResponse(Response.responseMessage("error",usernameAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(EmptyRoleException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response emptyRole(EmptyRoleException emptyRoleException){
        emptyRoleException.printStackTrace();
        return Response.badRequest().setResponse(Response.responseMessage("error",emptyRoleException.getMessage()));
    }

    @ExceptionHandler(TeamAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Response teamAlreadyExist(TeamAlreadyExistException teamAlreadyExistException){
        teamAlreadyExistException.printStackTrace();
        return Response.badRequest().setResponse(Response.responseMessage("error",teamAlreadyExistException.getMessage()));
    }

    @ExceptionHandler(TeamsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Response teamsNotFoundException(TeamsNotFoundException teamsNotFoundException){
        teamsNotFoundException.printStackTrace();
        return Response.notFound().setResponse(Response.responseMessage("error",teamsNotFoundException.getMessage()));
    }

}

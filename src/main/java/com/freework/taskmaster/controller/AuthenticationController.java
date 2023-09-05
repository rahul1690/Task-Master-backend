package com.freework.taskmaster.controller;

import com.freework.taskmaster.model.LoginModel;
import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.Response;
import com.freework.taskmaster.serviceImpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.freework.taskmaster.service.ResponseMessages.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/register")
    public Response registerUser(@Valid @RequestBody RegisterUserModel registerUserModel){
        return Response.ok().setPayload(userService.addUser(registerUserModel)).setResponse(Response.responseMessage("success", ADD_USER_SUCCESS));
    }

    @PostMapping("/login")
    public Response authenticateUser(@Valid @RequestBody LoginModel loginModel){
        return Response.ok().setPayload(userService.authenticateUser(loginModel));
    }
}

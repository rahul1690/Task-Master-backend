package com.freework.taskmaster.controller;

import com.freework.taskmaster.exceptions.EmptyRoleException;
import com.freework.taskmaster.model.LoginModel;
import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.Response;
import com.freework.taskmaster.model.RoleModel;
import com.freework.taskmaster.serviceImpl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.freework.taskmaster.service.ResponseMessages.*;


@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    private AuthServiceImpl userService;

    @PostMapping("/register")
    public Response registerUser(@Valid @RequestBody RegisterUserModel registerUserModel){
        try {
            RoleModel role = registerUserModel.getRole();
            if(role==null || role.getId()== null){
                throw new EmptyRoleException(EMPTY_ROLE);
            }
            return Response.ok().setPayload(userService.addUser(registerUserModel)).setResponse(Response.responseMessage("success", ADD_USER_SUCCESS));
        } catch(NullPointerException | EmptyRoleException e){
            e.printStackTrace();
            log.error("Role Exception-> {}",e.getMessage());
            return Response.badRequest().setResponse(Response.responseMessage("error",e.getMessage()));
        }
    }

    @PostMapping("/login")
    public Response authenticateUser(@Valid @RequestBody LoginModel loginModel){
        return Response.ok().setPayload(userService.authenticateUser(loginModel));
    }
}

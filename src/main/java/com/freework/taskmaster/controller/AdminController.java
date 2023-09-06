package com.freework.taskmaster.controller;

import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.Response;
import com.freework.taskmaster.serviceImpl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/get-users")
    public Response getAllUsers(@RequestParam String username){
        return Response.ok().setPayload(adminService.getAllUsers(username));
    }

    @PostMapping("/add-user")
    public Response addUser(@RequestParam String username,@Valid @RequestBody RegisterUserModel registerUserModel){
        return Response.ok().setPayload(adminService.addUser(username,registerUserModel));
    }

    @DeleteMapping("delete-user/{userId}")
    public Response deleteUser(@PathVariable Long userId){
        return Response.ok().setResponse(Response.responseMessage("success",adminService.deleteUser(userId)));
    }
}

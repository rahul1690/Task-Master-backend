package com.freework.taskmaster.serviceImpl;

import com.freework.taskmaster.entity.UserEntity;
import com.freework.taskmaster.exceptions.UserNotFoundException;
import com.freework.taskmaster.exceptions.UsernameAlreadyExistsException;
import com.freework.taskmaster.model.LoginModel;
import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.UserModel;
import com.freework.taskmaster.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.freework.taskmaster.service.ResponseMessages.*;
@Service
public class AuthServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserModel addUser(RegisterUserModel registerUserModel){

        if(userRepository.findByUsername(registerUserModel.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException(String.format(USER_ALREADY_EXIST,registerUserModel.getUsername()));
        }
        UserEntity userEntity = userRepository.save(modelMapper.map(registerUserModel, UserEntity.class));
        return modelMapper.map(userEntity,UserModel.class);
    }

    public UserModel authenticateUser(LoginModel loginModel){
        UserEntity userEntity = userRepository.findByUsername(loginModel.getUsername())
            .orElseThrow(()-> new UserNotFoundException(INVALID_USERNAME));
        if(loginModel.getPassword().equals(userEntity.getPassword())){
            return modelMapper.map(userEntity,UserModel.class);
        } else{
            throw new UserNotFoundException(INVALID_PASSWORD);
        }
    }

}

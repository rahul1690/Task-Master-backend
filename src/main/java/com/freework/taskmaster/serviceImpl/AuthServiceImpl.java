package com.freework.taskmaster.serviceImpl;
import com.freework.taskmaster.entity.UserEntity;

import com.freework.taskmaster.exceptions.UserNotFoundException;
import com.freework.taskmaster.exceptions.UsernameAlreadyExistsException;
import com.freework.taskmaster.model.AuthenticationResponse;
import com.freework.taskmaster.model.LoginModel;
import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.UserModel;
import com.freework.taskmaster.repository.UserRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.freework.taskmaster.service.ResponseMessages.*;

@Service
public class AuthServiceImpl{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserDetailService userDetailService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtilService jwtUtilService;
    

    public UserModel addUser(RegisterUserModel registerUserModel){

        if(userRepository.findByUsername(registerUserModel.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException(String.format(USER_ALREADY_EXIST,registerUserModel.getUsername()));
        }
        registerUserModel.setPassword(bCryptPasswordEncoder.encode(registerUserModel.getPassword()));
        UserEntity userEntity = userRepository.save(modelMapper.map(registerUserModel, UserEntity.class));
        return modelMapper.map(userEntity,UserModel.class);
    }

    public AuthenticationResponse authenticateUser(LoginModel loginModel){
        
    Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
    if(auth==null) {
		throw new UsernameNotFoundException(INVALID_PASSWORD);
	}
    final UserDetails userDetails = userDetailService.loadUserByUsername(loginModel.getUsername());

	final String token = jwtUtilService.generateToken(userDetails);

	return new AuthenticationResponse(token);
    }



}

package com.freework.taskmaster.serviceImpl;

import static com.freework.taskmaster.service.ResponseMessages.DELETED_USER;

import com.freework.taskmaster.entity.UserEntity;
import com.freework.taskmaster.model.RegisterUserModel;
import com.freework.taskmaster.model.UserModel;
import com.freework.taskmaster.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Set<UserModel> getAllUsers(String username) {
        UserEntity admin = userRepository.findByUsername(username).get();
        return admin.getUsers().stream()
            .map(userEntity-> modelMapper.map(userEntity, UserModel.class)).collect(Collectors.toSet());
    }

    public UserModel addUser(String username, RegisterUserModel registerUserModel) {
        UserEntity admin = userRepository.findByUsername(username).get();
        UserEntity user = modelMapper.map(registerUserModel, UserEntity.class);
        user.setAdmin(admin);
        return modelMapper.map(userRepository.save(user), UserModel.class);
    }

    public String deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return String.format(DELETED_USER,userId);
    }
}

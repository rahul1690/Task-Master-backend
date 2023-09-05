package com.freework.taskmaster.model;

import com.freework.taskmaster.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String designation;
    private RoleModel role;
}

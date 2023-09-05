package com.freework.taskmaster.model;

import com.freework.taskmaster.entity.RoleEntity;
import com.freework.taskmaster.exceptions.EmptyRoleException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserModel {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String mobile;
    private String designation;
    @Setter(AccessLevel.NONE) private RoleModel role;

    public void setRole(RoleModel role){
        if(role==null || role.getId()==null){
            throw new EmptyRoleException("Role Should Not Be Empty");
        }
        this.role= role;
    }

}

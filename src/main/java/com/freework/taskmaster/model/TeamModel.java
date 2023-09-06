package com.freework.taskmaster.model;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamModel {
    private Long id;
    @NotEmpty
    private String teamName;
    private UserModel admin;
    private Set<UserModel> participants;

}

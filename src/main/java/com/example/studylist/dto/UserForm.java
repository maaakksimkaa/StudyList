package com.example.studylist.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {
    private List<UserDTO> users;
    private Long groupNumber;
    private Long subjectNumber;

    // Геттеры и сеттеры
    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> userDTOs) {
        this.users = userDTOs;
    }

}

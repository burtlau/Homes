package com.project.homes.Web;

import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    public UserDto()
    {

    }

    public UserDto(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

}
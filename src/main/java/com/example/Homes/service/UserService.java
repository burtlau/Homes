package com.example.Homes.service;

import com.example.Homes.Web.UserDto;
import com.example.Homes.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);

}

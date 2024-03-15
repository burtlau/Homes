package com.project.homes.service;

import com.project.homes.Web.UserDto;
import com.project.homes.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserDto userDto);

}

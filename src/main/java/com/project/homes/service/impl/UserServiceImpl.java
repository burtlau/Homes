package com.project.homes.service.impl;

import com.project.homes.Web.UserDto;
import com.project.homes.entity.User;
import com.project.homes.repo.UserRepository;
import com.project.homes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUserName(), passwordEncoder.encode(userDto.getPassword()), userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        else {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singleton(authority));
        }
    }
}

package com.example.Homes.Controller;

import com.example.Homes.Web.UserDto;
import com.example.Homes.entity.User;
import com.example.Homes.repo.UserRepository;
import com.example.Homes.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
        try {
            userService.save(registrationDto);
            return "redirect:/registration?success";
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace(); // Example: Print the stack trace
            return "redirect:/registration?error";
        }
    }

    @ExceptionHandler(value=RuntimeException.class)
    @ResponseStatus(value=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }
}

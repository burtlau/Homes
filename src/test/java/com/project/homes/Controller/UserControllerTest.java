package com.project.homes.Controller;

import static org.mockito.Mockito.*;

import com.project.homes.TestConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.project.homes.Web.UserDto;
import com.project.homes.entity.User;
import com.project.homes.repo.UserRepository;
import com.project.homes.service.UserService;

class UserControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User USER_1 = TestConstants.USER_1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUserAccount_Success() {
        UserDto userDto = new UserDto();
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("redirect:/registration?success", HttpStatus.OK);
        when(userService.save(userDto)).thenReturn(USER_1);

        String actualRedirectUrl = userController.registerUserAccount(userDto);

        assertEquals("redirect:/registration?success", actualRedirectUrl);
    }

    @Test
    void testRegisterUserAccount_Failure() {
        UserDto userDto = new UserDto();
        doThrow(RuntimeException.class).when(userService).save(userDto);

        String actualRedirectUrl = userController.registerUserAccount(userDto);

        assertEquals("redirect:/registration?error", actualRedirectUrl);
    }

}
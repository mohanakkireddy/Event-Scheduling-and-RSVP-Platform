package com.rsvp.authentication;

import com.rsvp.authentication.controller.UserController;
import com.rsvp.authentication.dto.LoginDTO;
import com.rsvp.authentication.dto.SignUpDTO;
import com.rsvp.authentication.entity.User;
import com.rsvp.authentication.repo.UserRepository;
import com.rsvp.authentication.response.LoginResponse;
import com.rsvp.authentication.response.SignUpResponse;
import com.rsvp.authentication.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void fetchUserTest(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"mohan", "mohan", "kumar"));

        when(userService.fetchUsers()).thenReturn(users);

        List<User> fetchedusers = userService.fetchUsers();

        verify(userService, times(1)).fetchUsers();

        assertEquals(users.get(0), fetchedusers.get(0));

    }

    @Test
    public void registerUserYTest_EmailNotExist(){
        SignUpDTO signUpDTO = new SignUpDTO(1, "mohan", "mohan", "password");
        SignUpResponse signUpResponse = new SignUpResponse("mohan", "Created", HttpStatus.CREATED);
        when(userRepository.existsByEmail(signUpDTO.getEmail())).thenReturn(false);
        when(userService.addUser(signUpDTO)).thenReturn(signUpResponse);

        ResponseEntity<?> response = userController.registerUser(signUpDTO);

        verify(userRepository, times(1)).existsByEmail(signUpDTO.getEmail());
        verify(userService, times(1)).addUser(signUpDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    public void registerUserYTest_Email_already_exist(){
        SignUpDTO signUpDTO = new SignUpDTO(1, "mohan", "mohan", "password");
        when(userRepository.existsByEmail(signUpDTO.getEmail())).thenReturn(true);

        ResponseEntity<?> response = userController.registerUser(signUpDTO);

        verify(userRepository, times(1)).existsByEmail(signUpDTO.getEmail());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(userService, never()).addUser(signUpDTO);
        assertEquals("Email already exists!", response.getBody());
    }

    @Test
    public void loginuserTest(){
        LoginDTO loginDTO = new LoginDTO("mohan", "mohan");
        LoginResponse loginResponse = new LoginResponse("Login Success", true);
        when(userService.loginUser(loginDTO)).thenReturn(loginResponse);

        ResponseEntity<?> response = userController.loginUser(loginDTO);

        verify(userService, times(1)).loginUser(loginDTO);
        assertEquals(loginResponse, response.getBody());

    }
}

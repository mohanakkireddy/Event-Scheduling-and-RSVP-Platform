package com.rsvp.authentication;

import com.rsvp.authentication.dto.LoginDTO;
import com.rsvp.authentication.dto.SignUpDTO;
import com.rsvp.authentication.entity.User;
import com.rsvp.authentication.repo.UserRepository;
import com.rsvp.authentication.response.LoginResponse;
import com.rsvp.authentication.response.SignUpResponse;
import com.rsvp.authentication.service.UserServiceIMPL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceIMPL userServiceIMPL;

    @Test
    public void deleteUserTest(){
        int id= 1;

        userServiceIMPL.deleteUserById(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void fetchUsersTest(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"mohan", "mohan", "kumar"));
        when(userRepository.findAll()).thenReturn(users);

       List<User> fetchedUsers = userServiceIMPL.fetchUsers();

        verify(userRepository, times(1)).findAll();
        assertEquals(users.size(), fetchedUsers.size());
        assertEquals(users.get(0), fetchedUsers.get(0));

    }

    @Test
    public void addUserTest(){
        SignUpDTO signUpDTO = new SignUpDTO(1, "mohan", "mohan", "mohan");
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("hashed password");
        SignUpResponse response = userServiceIMPL.addUser(signUpDTO);

        verify(userRepository, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode(any(CharSequence.class));
        assertEquals("mohan", response.getUsername());
        assertEquals("User registered successfully", response.getMessage());
        assertEquals(HttpStatus.CREATED, response.getStatus());

    }

    @Test
    public void loginUserTest_Success(){
        String email = "mohan";
        String password ="mohan";
        String encodedPassword = passwordEncoder.encode(password);

        LoginDTO loginDTO = new LoginDTO(email, password);
        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(encodedPassword);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(userRepository.findOneByEmailAndPassword(email, encodedPassword)).thenReturn(Optional.of(mockUser));

        LoginResponse response = userServiceIMPL.loginUser(loginDTO);

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).findOneByEmailAndPassword(email, encodedPassword);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);

        assertEquals("Login Success", response.getMessage());
        assertEquals(true, response.getStatus());
    }
    @Test
    public void loginUserTest_Fail(){
        String email = "mohan";
        String password ="mohan";
        String encodedPassword = passwordEncoder.encode(password);
        LoginDTO loginDTO = new LoginDTO(email, password);
        User mockUser = new User();
        mockUser.setEmail(email);
        mockUser.setPassword(encodedPassword);
        when(userRepository.findByEmail(email)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        LoginResponse response = userServiceIMPL.loginUser(loginDTO);

        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
        assertEquals("password Not Match", response.getMessage());
        assertEquals(false, response.getStatus());
    }

    @Test
    public void loginUser_NoEmail(){
        String email = "mohan";
        String password ="mohan";
        LoginDTO loginDTO = new LoginDTO(email, password);
        when(userRepository.findByEmail(email)).thenReturn(null);

        LoginResponse response = userServiceIMPL.loginUser(loginDTO);


        verify(userRepository, times(1)).findByEmail(email);
        assertEquals("Email not exits", response.getMessage());
        assertEquals(false, response.getStatus());

    }
}

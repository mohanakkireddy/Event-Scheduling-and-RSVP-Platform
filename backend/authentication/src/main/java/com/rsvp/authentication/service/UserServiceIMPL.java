package com.rsvp.authentication.service;

import com.rsvp.authentication.dto.LoginDTO;
import com.rsvp.authentication.dto.SignUpDTO;
import com.rsvp.authentication.entity.User;
import com.rsvp.authentication.repo.UserRepository;
import com.rsvp.authentication.response.LoginResponse;
import com.rsvp.authentication.response.SignUpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIMPL implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SignUpResponse addUser(SignUpDTO signUpDTO) {
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getEmail(),
                this.passwordEncoder.encode(signUpDTO.getPassword())
        );
        userRepository.save(user);
        return new SignUpResponse(user.getName(), "User registered successfully", HttpStatus.CREATED);
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        }else {
            return new LoginResponse("Email not exits", false);
        }
    }

    @Override
    public List<User> fetchUsers() {
        return (List<User>)
                userRepository.findAll();
    }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}

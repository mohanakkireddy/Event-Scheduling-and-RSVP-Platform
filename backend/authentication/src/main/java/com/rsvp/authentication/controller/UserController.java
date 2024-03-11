package com.rsvp.authentication.controller;

import com.rsvp.authentication.dto.LoginDTO;
import com.rsvp.authentication.dto.SignUpDTO;
import com.rsvp.authentication.entity.User;
import com.rsvp.authentication.repo.UserRepository;
import com.rsvp.authentication.response.LoginResponse;
import com.rsvp.authentication.response.SignUpResponse;
import com.rsvp.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO){
        if (userRepository.existsByEmail(signUpDTO.getEmail())){
            return new ResponseEntity<>("Email already exists!", HttpStatus.BAD_REQUEST);
        }
        SignUpResponse response = userService.addUser(signUpDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @GetMapping(path = "/view")
    public List<User> fetchUsers(){
        return userService.fetchUsers();
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUserById(id);
    }
}


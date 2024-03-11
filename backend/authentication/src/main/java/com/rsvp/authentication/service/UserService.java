package com.rsvp.authentication.service;

import com.rsvp.authentication.dto.LoginDTO;
import com.rsvp.authentication.dto.SignUpDTO;
import com.rsvp.authentication.entity.User;
import com.rsvp.authentication.response.LoginResponse;
import com.rsvp.authentication.response.SignUpResponse;

import java.util.List;

public interface UserService {
    SignUpResponse addUser(SignUpDTO newUserDTO);

    LoginResponse loginUser(LoginDTO loginUserDTO);

    List<User> fetchUsers();

    void deleteUserById(int id);
}

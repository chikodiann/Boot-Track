package com.ann.BootTrackActivityTracker.service;

import com.ann.BootTrackActivityTracker.dto.LoginDTO;
import com.ann.BootTrackActivityTracker.dto.UserDTO;
import com.ann.BootTrackActivityTracker.entity.User;

import java.util.List;

public interface UserService {
    boolean registerUser(UserDTO userDTO);

    void signUpUser(UserDTO userDTO);
    UserDTO loginUser(LoginDTO loginDTO);
    User getUserByEmail(String email);

    User getUserByUsername(String userName);
     User getUserByPassword(String password);

    UserDTO getUserById(Long userId);

    List<User> getAllUsers();
}

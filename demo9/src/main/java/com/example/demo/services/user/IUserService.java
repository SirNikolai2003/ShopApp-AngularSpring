package com.example.demo.services.user;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entity.User;

public interface IUserService {
    User createUser(UserDTO userDTO);
    String login(String phoneNumber, String password);
}

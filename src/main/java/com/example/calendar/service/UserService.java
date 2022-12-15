package com.example.calendar.service;

import com.example.calendar.model.User;
import com.example.calendar.model.dto.UserDTO;
import com.example.calendar.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public String checkCredentials(UserDTO userDTO) {
        User user = userRepository.getById(userDTO.getUsername());
        if (user.getPassword().equals(userDTO.getPassword())) {
            return user.getUsername();
        }
        return null;
    }
}

package com.example.calendar.service;

import com.example.calendar.model.User;
import com.example.calendar.model.dto.RegisterUserDTO;
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

    public User addUser(RegisterUserDTO registerUserDTO) {
        return userRepository.save(convertRegisterToUser(registerUserDTO));
    }

    private User convertRegisterToUser(RegisterUserDTO registerUserDTO) {
        User u = new User();
        u.setUsername(registerUserDTO.getUsername());
        u.setPassword(registerUserDTO.getPassword());
        u.setFirstName(registerUserDTO.getFirstName());
        u.setLastName(registerUserDTO.getLastName());
        return u;

    }
}

package com.example.calendar.service;

import com.example.calendar.model.User;
import com.example.calendar.model.dto.RegisterUserDTO;
import com.example.calendar.model.dto.UserDTO;
import com.example.calendar.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public String checkCredentials(UserDTO userDTO) {
        Optional<User> user = userRepository.getUserByUsername(userDTO.getUsername());
        if (user.isEmpty()){
            return null;
        }
        if (user.get().getPassword().equals(userDTO.getPassword())) {
            return user.get().getUsername();
        }
        return null;
    }

    public User addUser(RegisterUserDTO registerUserDTO) {
        Optional<User> user = userRepository.getUserByUsername(registerUserDTO.getUsername());
        if (user.isPresent()){
            return null;
        }
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

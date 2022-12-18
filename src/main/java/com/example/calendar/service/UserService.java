package com.example.calendar.service;

import com.example.calendar.model.User;
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
}

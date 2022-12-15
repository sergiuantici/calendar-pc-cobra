package com.example.calendar.controller;

import com.example.calendar.model.dto.UserDTO;
import com.example.calendar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Resource
    private UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        String response = userService.checkCredentials(userDTO);
        if (response == null) {
            return new ResponseEntity<>("Invalid credentials!", HttpStatus.FORBIDDEN);
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

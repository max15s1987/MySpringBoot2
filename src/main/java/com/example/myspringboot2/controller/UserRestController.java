package com.example.myspringboot2.controller;

import com.example.myspringboot2.converter.UserConverter;
import com.example.myspringboot2.dto.UserDTO;
import com.example.myspringboot2.model.User;
import com.example.myspringboot2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserServiceImpl userService;

    @Autowired
    private UserConverter userConverter;

    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getPeopleList() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(userConverter.entityToDo(allUsers));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(userConverter.entityToDto(user));
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addNewUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userConverter.entityToDto(user));
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(userConverter.entityToDto(user));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
    }
}



package com.example.myspringboot2.controller;

import com.example.myspringboot2.converter.UserConverter;
import com.example.myspringboot2.dto.UserDTO;
import com.example.myspringboot2.model.User;
import com.example.myspringboot2.service.RoleService;
import com.example.myspringboot2.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<UserDTO> getPeopleList() {
        List<User> allUsers = userService.getAllUsers();
        return userConverter.entityToDo(allUsers);
    }

    @GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return userConverter.entityToDto(user);
    }

    @PostMapping("/users")
    public UserDTO addNewUser(@RequestBody User user) {
        userService.save(user);
        return userConverter.entityToDto(user);
    }

    @PutMapping("/users")
    public UserDTO updateUser(@RequestBody User user) {
        userService.update(user);
        return userConverter.entityToDto(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
    }
}



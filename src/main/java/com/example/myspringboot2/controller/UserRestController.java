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
    private final RoleService roleService;

    @Autowired
    private UserConverter userConverter;

    public UserRestController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<UserDTO> getPeopleList() {
        List<User> allUsers = userService.getAllUsers();
        return userConverter.entityToDo(allUsers);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
    }
}



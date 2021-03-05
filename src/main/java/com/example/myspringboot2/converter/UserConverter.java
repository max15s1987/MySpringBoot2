package com.example.myspringboot2.converter;

import com.example.myspringboot2.dto.RoleDTO;
import com.example.myspringboot2.dto.UserDTO;
import com.example.myspringboot2.model.Role;
import com.example.myspringboot2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private RoleConverter roleConverter;

    public UserDTO entityToDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(roleConverter.toDTOs(new ArrayList<>(user.getRoles())));

        return userDTO;
    }


    public List<UserDTO> entityToDo(List<User> users) {
        return users.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDTO userDTO) {

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setAge(userDTO.getAge());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
//        user.setRoles(userDTO.getRoles().stream().collect(Collectors.toSet()));

        return user;
    }

    public List<User> dtoToEntity(List<UserDTO> usersDTO) {
        return usersDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }



}

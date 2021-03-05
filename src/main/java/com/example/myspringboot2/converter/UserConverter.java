package com.example.myspringboot2.converter;

import com.example.myspringboot2.dto.UserDTO;
import com.example.myspringboot2.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public UserDTO entityToDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());

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
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());

        return user;
    }

    public List<User> dtoToEntity(List<UserDTO> usersDTO) {
        return usersDTO.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }



}

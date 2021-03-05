package com.example.myspringboot2.dto;

import com.example.myspringboot2.model.Role;

import java.util.List;

public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private Integer age;
    private String email;
    private String password;
    private List<RoleDTO> roles;

    public UserDTO() {

    }

    public UserDTO(Long id, String name, String lastName, Integer age, String email, String password, List<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}

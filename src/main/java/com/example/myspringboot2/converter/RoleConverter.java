package com.example.myspringboot2.converter;

import com.example.myspringboot2.dto.RoleDTO;
import com.example.myspringboot2.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleConverter {

    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());

        return roleDTO;
    }

    public List<RoleDTO> toDTOs(List<Role> roles) {
        return roles.stream().map(role -> toDTO(role)).collect(Collectors.toList());
    }
}

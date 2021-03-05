package com.example.myspringboot2.controller;


import com.example.myspringboot2.model.User;
import com.example.myspringboot2.service.RoleService;
import com.example.myspringboot2.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userService;
    private final RoleService roleService;

    public UserController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getPeopleList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("userInfo", userService.getUserById(id));

        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }


    @PostMapping("/new")
    public String createUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }



    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam(value ="id") Long id) {

        if (userService.checkId(id)) {
            return "unknownUser";
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserById(Model model) {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("userInfo", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {

        if (userService.checkId(id)) {
            return "unknownUser";
        }

        userService.remove(id);
        return "redirect:/admin";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id) {
        return userService.findById(id);
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

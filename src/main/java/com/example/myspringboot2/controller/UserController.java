package com.example.myspringboot2.controller;


import com.example.myspringboot2.model.User;
import com.example.myspringboot2.service.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getPeopleList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        return "new";
    }


    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam(value ="id") Long id) {

        if (userService.checkId(id)) {
            return "unknownUser";
        }

        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam(value ="id") Long id) {

        if (userService.checkId(id)) {
            return "unknownUser";
        }

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserById(Model model) {
        Long id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        model.addAttribute("user", userService.getUserById(id));
        return "showuser";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") Long id) {

        if (userService.checkId(id)) {
            return "unknownUser";
        }

        userService.remove(id);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}

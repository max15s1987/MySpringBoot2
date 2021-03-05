package com.example.myspringboot2.service;


import com.example.myspringboot2.exceptionHandling.NoSuchUserException;
import com.example.myspringboot2.model.User;
import com.example.myspringboot2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void update(User user) {
        if (!user.getPassword().equals(userRepository.getOne(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAndFlush(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public boolean checkId(Long id) {
        return !userRepository.existsById(id);
    }

    public User findById(long id) {
        User user;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new NoSuchUserException("There is no user with ID = " + id + " in Database");
        }
        return user;
    }


}

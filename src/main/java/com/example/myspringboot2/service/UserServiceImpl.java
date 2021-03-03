package com.example.myspringboot2.service;


import com.example.myspringboot2.model.User;
import com.example.myspringboot2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void update(Long id, User user) {

        User userUpdate = getUserById(id);

        userUpdate.setName(user.getName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setAge(user.getAge());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setRoles(user.getRoles());

        if (user.getPassword().equals(userUpdate.getPassword())) {
            userRepository.save(userUpdate);
        } else {
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userUpdate);
        }
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
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid filledUser Id:" + id));
    }


}

package com.rodrigo.backend.services;

import com.rodrigo.backend.models.User;
import com.rodrigo.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User saveUser(String name, String lastName) {
        return userRepository.save(new User(name, lastName));
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

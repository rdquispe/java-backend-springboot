package com.rodrigo.backend.controllers;

import com.rodrigo.backend.models.User;
import com.rodrigo.backend.requests.UserRequest;
import com.rodrigo.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.rodrigo.backend.Routes.BASE_API_VERSION;
import static com.rodrigo.backend.Routes.USERS;
import static com.rodrigo.backend.Routes.USER_ID;

@RestController
@RequestMapping(BASE_API_VERSION)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(USERS)
    @ResponseBody
    public User createUser(@RequestBody UserRequest request) {
        return userService.saveUser(request.getName(), request.getLastName());
    }

    @GetMapping(USERS)
    public List<User> getListUsers() {
        return userService.allUsers();
    }

    @GetMapping(USER_ID)
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping(USER_ID)
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}

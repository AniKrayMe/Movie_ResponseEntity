package com.example.demo.controller;

import com.example.demo.moddel.User;
import com.example.demo.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping(value = "/user/{id}")
    public void get(@PathVariable int id) {
        userService.findById(id);
    }
    @GetMapping(value = "/user")
    public ArrayList<User> getAll(){
        return userService.findAll();
    }
    @PutMapping(value = "/user/{id}")
    public void update(@PathVariable int id , @RequestBody  User user){
        userService.updateById(id, user);
    }
    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable int id){
        userService.deleteById(id);
    }
}

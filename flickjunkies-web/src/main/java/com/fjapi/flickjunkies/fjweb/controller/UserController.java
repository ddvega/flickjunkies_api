package com.fjapi.flickjunkies.fjweb.controller;

import com.fjapi.flickjunkies.fjcore.entity.User;
import com.fjapi.flickjunkies.fjcore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping
    public User getUserFromToken()
    {
        return userService.getUserFromToken();
    }

    @PostMapping("/new")
    public String addUser(@RequestBody User payload)
    {
        return userService.addUser(payload);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id)
    {
        return userService.deleteUser(id);
    }
}

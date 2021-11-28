package com.fjapi.flickjunkies.controller;

import com.fjapi.flickjunkies.config.UserService;
import com.fjapi.flickjunkies.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController
{
    private final UserService userService;

    @PostMapping
    public String addUser(@RequestBody User payload)
    {
        return userService.addUser(payload);
    }
}

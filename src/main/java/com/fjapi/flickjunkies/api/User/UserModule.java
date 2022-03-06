package com.fjapi.flickjunkies.api.User;

import com.fjapi.flickjunkies.model.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserModule
{
    @GetMapping
    User getUserFromToken();

    @PostMapping("/new")
    String addUser(@RequestBody User payload);

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable("id") Long id);
}

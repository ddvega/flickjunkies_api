package com.fjapi.flickjunkies.api.User;

import com.fjapi.flickjunkies.model.User;
import com.fjapi.flickjunkies.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserModuleImpl implements UserModule
{
    private final UserService userService;

    public UserModuleImpl(UserService userService) {this.userService = userService;}

    @Override
    public User getUserFromToken(){return userService.getUserFromToken();}

    @Override
    public String addUser(@RequestBody User payload){return userService.addUser(payload);}

    @Override
    public String deleteUser(@PathVariable("id") Long id){return userService.deleteUser(id);}
}

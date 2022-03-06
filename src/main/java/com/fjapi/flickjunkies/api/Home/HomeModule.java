package com.fjapi.flickjunkies.api.Home;

import com.fjapi.flickjunkies.model.JwtRequest;
import com.fjapi.flickjunkies.model.JwtResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HomeModule
{
    @GetMapping("/")
    String home();

    @PostMapping("/authenticate")
    JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception;

}

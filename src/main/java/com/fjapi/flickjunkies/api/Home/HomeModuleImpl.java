package com.fjapi.flickjunkies.api.Home;

import com.fjapi.flickjunkies.model.JwtRequest;
import com.fjapi.flickjunkies.model.JwtResponse;
import com.fjapi.flickjunkies.service.UserService;
import com.fjapi.flickjunkies.util.JWTUtility;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeModuleImpl implements HomeModule
{
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public HomeModuleImpl(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService)
    {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public String home()
    {
        return "welcome home!";
    }

    @Override
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e)
        {
            throw new Exception("Invalid credentials", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }
}

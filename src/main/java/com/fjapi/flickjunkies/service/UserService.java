package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.entity.User;
import com.fjapi.flickjunkies.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
@Service
public class UserService
{
    private final UserRepository userRepository;

    public String addUser(User user)
    {
        userRepository.save(user);
        return "user added";
    }
}

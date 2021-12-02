package com.fjapi.flickjunkies.service;

import com.fjapi.flickjunkies.config.CustomUserDetails;
import com.fjapi.flickjunkies.entity.User;
import com.fjapi.flickjunkies.repository.LibraryRepository;
import com.fjapi.flickjunkies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryRepository libraryRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found.");

        return new CustomUserDetails(user);
    }

    public User getUserFromToken()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByUsername(userDetails.getUsername());
    }

    public String addUser(User user)
    {
        User storedUser = userRepository.findUserByUsername(user.getUsername());
        if (storedUser != null)
            return "User " + user.getUsername() + " already exists in database.";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getUsername() + " added to database.";
    }

    @Transactional // needed this to delete all user libraries before deleting user
    public String deleteUser(Long id)
    {
        User user = userRepository.getById(id);
        libraryRepository.removeAllByUserEquals(user);
        userRepository.delete(user);
        return "user deleted";
    }
}

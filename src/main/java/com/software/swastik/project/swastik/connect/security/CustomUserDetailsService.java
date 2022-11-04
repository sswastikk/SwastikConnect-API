package com.software.swastik.project.swastik.connect.security;

import com.software.swastik.project.swastik.connect.entities.User;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findByEmailId(username).orElseThrow(() -> new ResourceNotFoundException("User", "emaill ", username));
        return user;
    }
}

package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.exceptions.ApiException;
import com.software.swastik.project.swastik.connect.payloads.JwtAuthRequest;
import com.software.swastik.project.swastik.connect.payloads.JwtAuthResponse;
import com.software.swastik.project.swastik.connect.payloads.UserDto;
import com.software.swastik.project.swastik.connect.security.JwtTokenHelper;
import com.software.swastik.project.swastik.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class LoginController
{
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> generateToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception
    {
        this.authenticate(jwtAuthRequest.getUserName(), jwtAuthRequest.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) throws Exception
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try{
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }catch(BadCredentialsException e){
            throw new ApiException("Invalid Credentials !!!");
        }
    }

    //Create a new User through POST Call
    @PostMapping("/register")
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createdUser = this.userService.registerNormalUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}

package com.software.swastik.project.swastik.connect.controllers;

import com.software.swastik.project.swastik.connect.payloads.ApiResponse;
import com.software.swastik.project.swastik.connect.payloads.UserDto;
import com.software.swastik.project.swastik.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    //Create a new User through POST Call
    @PostMapping("/")
    public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createdUser = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //Update an Existing User through PUT Call
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateExistingUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long userId)
    {
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //Delete a User through DELETE Call
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully", true, "Ok"), HttpStatus.OK);
    }

    //Get All Users through GET Call
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    //Get Specified User through GET Call
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Long userId)
    {
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
}

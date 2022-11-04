package com.software.swastik.project.swastik.connect.services;

import com.software.swastik.project.swastik.connect.payloads.UserDto;
import java.util.List;

public interface UserService
{
    //Register User With Password Encoding
    UserDto registerNormalUser(UserDto userDto);

    //Create a New User
    UserDto createUser(UserDto userDto);

    //Update an Existing User
    UserDto updateUser(UserDto userDto, Long userId);

    //Get User Details by UserId
    UserDto getUserById(Long userId);

    //Get All User Details
    List<UserDto> getAllUsers();

    //Delete User By User Id
    void deleteUser(Long userId);
}

package com.software.swastik.project.swastik.connect.services.implementations;

import com.software.swastik.project.swastik.connect.configurations.AppConstants;
import com.software.swastik.project.swastik.connect.entities.Role;
import com.software.swastik.project.swastik.connect.entities.User;
import com.software.swastik.project.swastik.connect.exceptions.ResourceNotFoundException;
import com.software.swastik.project.swastik.connect.payloads.UserDto;
import com.software.swastik.project.swastik.connect.repositories.RoleRepository;
import com.software.swastik.project.swastik.connect.repositories.UserRepository;
import com.software.swastik.project.swastik.connect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto registerNormalUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Date createdOn =  new Date();
        user.setCreatedOn(createdOn);
        user.setModifiedOn(createdOn);

        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);

        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        Date createdOn =  new Date();
        user.setCreatedOn(createdOn);
        user.setModifiedOn(createdOn);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmailId(userDto.getEmailId());
        user.setContactNumber(userDto.getContactNumber());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setBiography(userDto.getBiography());
        Date modifiedOn =  new Date();
        user.setModifiedOn(modifiedOn);

        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        this.userRepository.deleteById(userId);
    }

    //Conversion Method UserDto to User
    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    //Conversion Method User to UserDto
    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}

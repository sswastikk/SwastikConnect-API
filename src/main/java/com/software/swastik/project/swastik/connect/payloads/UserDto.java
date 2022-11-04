package com.software.swastik.project.swastik.connect.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto
{
    private Long userId;

    @NotEmpty(message = "First Name cannot be empty !!!")
    @Size(min = 4 , max = 100, message = "First Name should be of Minimum 4 Characters and Maximum 100 Characters !!!")
    private String firstName;

    @Size(max = 100, message = "Middle Name should be of Maximum 100 Characters !!!")
    private String middleName;

    @NotEmpty(message = "Last Name cannot be empty !!!")
    @Size(min = 4 , max = 100, message = "Last Name should be of Minimum 4 Characters and Maximum 100 Characters !!!")
    private String lastName;

    @Email(message = "Email address is invalid !!!")
    @NotEmpty(message = "EmailId cannot be empty !!!")
    private String emailId;

    @NotEmpty(message = "Contact Number cannot be empty !!!")
    @Size(min=10, max = 10, message = "Contact Number cannot be more than 10 Digits !!!")
    private String contactNumber;

    private Date dateOfBirth;

    private String profilePicture = "default.png";

    @Size(max = 1000, message = "Biography cannot be more than 1000 characters !!!")
    private String biography;

    @NotEmpty(message = "Password cannot be empty !!!")
    @Size(min = 8, max = 16, message = "Password should be of minimum 8 characters and maximum 16 characters !!!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    private Date createdOn;
    private Date modifiedOn;

    private Set<RoleDto> roles = new HashSet<>();
}

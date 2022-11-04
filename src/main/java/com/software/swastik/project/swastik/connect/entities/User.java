package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long userId;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "MiddleName", nullable = true, length = 100)
    private String middleName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "EmailId", nullable = false, length = 50, unique = true)
    private String emailId;

    @Column(name = "ContactNumber", nullable = false, length = 20, unique = true)
    private String contactNumber;

    @Column(name = "DateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "ProfilePicture")
    private String profilePicture = "default.png";

    @Column(name = "Biography", length = 1000)
    private String biography;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @Column(name = "CreatedOn", nullable = false)
    private Date createdOn;

    @Column(name = "ModifiedOn", nullable = false)
    private Date modifiedOn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Roles",
            joinColumns=@JoinColumn(name = "User", referencedColumnName = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Role", referencedColumnName = "Role_Id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

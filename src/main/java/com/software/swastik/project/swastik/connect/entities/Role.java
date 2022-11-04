package com.software.swastik.project.swastik.connect.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Roles")
@NoArgsConstructor
@Getter
@Setter
public class Role
{
    @Id
    @Column(name = "RoleId")
    private Long roleId;

    @Column(name = "RoleName")
    private String roleName;
}

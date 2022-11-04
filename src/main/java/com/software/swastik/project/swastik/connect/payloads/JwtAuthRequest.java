package com.software.swastik.project.swastik.connect.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest
{
    private String userName;
    private String password;
}

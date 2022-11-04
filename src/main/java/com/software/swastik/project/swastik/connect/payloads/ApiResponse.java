package com.software.swastik.project.swastik.connect.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse
{
    private String message;
    private Boolean success;
    private String statusCode;
}

package com.software.swastik.project.swastik.connect.exceptions;

public class ApiException extends RuntimeException
{
    public ApiException(String message) {
        super(message);
    }

    public ApiException() {
    }
}

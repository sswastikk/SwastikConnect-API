package com.software.swastik.project.swastik.connect.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException
{
    private String resourceName;
    private String resourceField;
    private Long fieldValue;
    private String field;

    public ResourceNotFoundException(String resourceName, String resourceField, Long fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, resourceField, fieldValue));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String resourceField, String field) {
        super(String.format("%s not found with %s: %s", resourceName, resourceField, field));
        this.resourceName = resourceName;
        this.resourceField = resourceField;
        this.field = field;
    }
}

package com.blogapp.Scribble_Hub.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;

    private Long longFieldValue;
    private String stringFieldValue;

    public ResourceNotFoundException(
            String resourceName,
            String fieldName,
            Long fieldValue) {

        super(resourceName + " not found with "
                + fieldName + " : "
                + fieldValue);

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.longFieldValue = fieldValue;
    }

    public ResourceNotFoundException(
            String resourceName,
            String fieldName,
            String fieldValue) {

        super(resourceName + " not found with "
                + fieldName + " : "
                + fieldValue);

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.stringFieldValue = fieldValue;
    }
}
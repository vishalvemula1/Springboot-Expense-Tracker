package dev.v.expensetracker.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HTTPException{
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}

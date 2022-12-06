package com.taskmanager.taskmanagerapp.exception;

import com.taskmanager.taskmanagerapp.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundExceptions(ResourceNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleAlreadyExistsExceptions(ResourceAlreadyExists ex){
        return new ApiResponse<>(ex.getMessage(), HttpStatus.CONFLICT.value(), null);
    }
}

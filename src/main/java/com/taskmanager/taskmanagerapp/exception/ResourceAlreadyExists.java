package com.taskmanager.taskmanagerapp.exception;

public class ResourceAlreadyExists extends RuntimeException{
    public ResourceAlreadyExists(String message) {
        super(message);
    }
}

package com.teksystem.employee.exceptions;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String msg) {
        super(msg);
    }
}

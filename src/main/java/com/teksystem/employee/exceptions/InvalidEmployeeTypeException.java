package com.teksystem.employee.exceptions;

public class InvalidEmployeeTypeException extends RuntimeException {

    public InvalidEmployeeTypeException(String message) {
        super(message);
    }
}

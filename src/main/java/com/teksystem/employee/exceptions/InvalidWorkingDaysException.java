package com.teksystem.employee.exceptions;

public class InvalidWorkingDaysException extends RuntimeException {

    public InvalidWorkingDaysException(String message) {
        super(message);
    }
}

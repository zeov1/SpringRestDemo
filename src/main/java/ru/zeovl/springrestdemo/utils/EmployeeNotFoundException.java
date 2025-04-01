package ru.zeovl.springrestdemo.utils;

public class EmployeeNotFoundException extends RuntimeException {

    private final int requestedEmployeeId;

    public EmployeeNotFoundException(int requestedEmployeeId) {
        this.requestedEmployeeId = requestedEmployeeId;
    }

    public int getRequestedEmployeeId() {
        return requestedEmployeeId;
    }
}

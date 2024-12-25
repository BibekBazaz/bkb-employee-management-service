package com.bibekbazaz.bkb_employee_management_service.api.model.errors;

public class EmployeeDoesNotExistException extends RuntimeException{

    private String errorMessage;

    public EmployeeDoesNotExistException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public EmployeeDoesNotExistException() {
    }
}

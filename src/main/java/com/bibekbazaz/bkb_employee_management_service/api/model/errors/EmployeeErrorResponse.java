package com.bibekbazaz.bkb_employee_management_service.api.model.errors;

import java.io.Serializable;

public class EmployeeErrorResponse implements Serializable {

    private int statusCode;
    private String errorMessage;

    public EmployeeErrorResponse(int statusCode) {
        this.statusCode = statusCode;
    }

    public EmployeeErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public EmployeeErrorResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

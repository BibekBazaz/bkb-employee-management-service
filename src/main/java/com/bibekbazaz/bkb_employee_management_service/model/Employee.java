package com.bibekbazaz.bkb_employee_management_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

@Entity
public class Employee {

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String designation;
    @NonNull
    private String department;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;

    public Employee() {
    }

    public Employee(String name, String email, String designation, String department, long employeeId) {
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.department = department;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Employee Details : " + this.getName() + " " + this.getEmail();
    }
}

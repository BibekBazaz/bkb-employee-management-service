package com.bibekbazaz.bkb_employee_management_service.api.controllers;

import com.bibekbazaz.bkb_employee_management_service.api.model.EmployeeRequestBody;
import com.bibekbazaz.bkb_employee_management_service.api.model.EmployeeResponseBody;
import com.bibekbazaz.bkb_employee_management_service.api.model.errors.EmployeeErrorResponse;
import com.bibekbazaz.bkb_employee_management_service.api.model.errors.EmployeeDoesNotExistException;
import com.bibekbazaz.bkb_employee_management_service.model.Employee;
import com.bibekbazaz.bkb_employee_management_service.services.EmployeeManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeManagementService employeeManagementService;
    private static  final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeManagementService employeeManagementService) {
        this.employeeManagementService = employeeManagementService;
    }

    @GetMapping(value = "/{employeeId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId, @RequestHeader Map<String, String> headers) {
        LOGGER.atInfo().setMessage("GET Employee method invoked, correlation id :  " + headers.get("correlation-id")).addKeyValue("Correlation-Id",headers.get("correlation-id") ).log();
        return new ResponseEntity<>(employeeManagementService.getEmployee(employeeId), HttpStatus.OK);
    }


    //CREATE Employee
    @PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseBody> createEmployee(@RequestBody EmployeeRequestBody employeeRequestBody) {
        LOGGER.info("Employee EMail : " + employeeRequestBody.getEmail());
        LOGGER.info("Employee Designation : " + employeeRequestBody.getDesignation());
        LOGGER.info("Employee Department : " + employeeRequestBody.getDesignation());
        LOGGER.info("Employee Name : " + employeeRequestBody.getName());
        Employee employee = new Employee();
        employee.setEmployeeId(employeeRequestBody.getEmployeeId());
        employee.setEmail(employeeRequestBody.getEmail());
        employee.setDepartment(employeeRequestBody.getDepartment());
        employee.setName(employeeRequestBody.getName());
        employee = employeeManagementService.addEmployee(employee);
        EmployeeResponseBody responseBody = new EmployeeResponseBody();
        responseBody.setDepartment(employee.getDepartment());
        responseBody.setEmployeeId(employee.getEmployeeId());
        responseBody.setDesignation(employee.getDesignation());
        responseBody.setEmail(employee.getEmail());
        responseBody.setName(employee.getName());
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);

    }


    // update employee details
    @PutMapping(value ="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponseBody> updateEmployee(@RequestBody EmployeeRequestBody employeeRequestBody) {
        LOGGER.info("Employee Name : " + employeeRequestBody.getName());
        LOGGER.info("Employee EMail : " + employeeRequestBody.getEmail());
        LOGGER.info("Employee Designation : " + employeeRequestBody.getDesignation());
        LOGGER.info("Employee Department : " + employeeRequestBody.getDesignation());
        Employee employee = new Employee();
        employee.setEmployeeId(employeeRequestBody.getEmployeeId());
        employee.setEmail(employeeRequestBody.getEmail());
        employee.setDepartment(employeeRequestBody.getDepartment());
        employee.setName(employeeRequestBody.getName());
        employee = employeeManagementService.updateEmployee(employee);
        EmployeeResponseBody responseBody = new EmployeeResponseBody();
        responseBody.setDepartment(employee.getDepartment());
        responseBody.setEmployeeId(employee.getEmployeeId());
        responseBody.setDesignation(employee.getDesignation());
        responseBody.setEmail(employee.getEmail());
        responseBody.setName(employee.getName());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

    //delete employee
    @DeleteMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeManagementService.deleteEmployee(employeeId);
        LOGGER.info("Delete successful");
        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
    }

    @ExceptionHandler(EmployeeDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeDoesNotExistException(EmployeeDoesNotExistException exception) {
        LOGGER.error("Handling EmployeeDoesNotExistException: " + exception.getMessage());
        EmployeeErrorResponse errorResponse = new EmployeeErrorResponse(404, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorResponse);
    }

}

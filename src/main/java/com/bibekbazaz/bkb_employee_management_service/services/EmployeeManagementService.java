package com.bibekbazaz.bkb_employee_management_service.services;

import com.bibekbazaz.bkb_employee_management_service.api.model.errors.EmployeeDoesNotExistException;
import com.bibekbazaz.bkb_employee_management_service.dao.EmployeeDAO;
import com.bibekbazaz.bkb_employee_management_service.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeManagementService {

    @Autowired
    private final EmployeeDAO employeeDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeManagementService.class);

    public EmployeeManagementService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee addEmployee(Employee employee) {
        Employee employee1 = employeeDAO.save(employee);
        LOGGER.info("Employee Inserted , Employee ID : " + employee1.getEmployeeId());
        return employee1;
    }

    public Employee getEmployee(Long employeeId) {
        if (employeeDAO.findById(employeeId).isPresent()) {
            return employeeDAO.findById(employeeId).get();
        } else {
            LOGGER.info("Employee Does Not Exist");
            LOGGER.atError().setMessage("Employee Does Not Exist").addKeyValue("employeeId",employeeId).log();
            throw new EmployeeDoesNotExistException("Employee Does Not Exist");
        }
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> employee1 = employeeDAO.findById(employee.getEmployeeId());
        if (employee1.isPresent()) {
            return employeeDAO.save(employee);

        } else {
            LOGGER.atInfo().setMessage("Bad Request").addKeyValue("employeeId",employee.getEmployeeId()).log();
            throw new EmployeeDoesNotExistException("Bad Request");
        }
    }

    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employee1 = employeeDAO.findById(employeeId);
        if (employee1.isPresent()) {
            employeeDAO.delete(employee1.get());
            LOGGER.info("Employee deleted successfully");
        } else {
            LOGGER.info("Bad Request");
            throw new EmployeeDoesNotExistException("Non Existing Employee,cannot be deleted");
        }
    }


}

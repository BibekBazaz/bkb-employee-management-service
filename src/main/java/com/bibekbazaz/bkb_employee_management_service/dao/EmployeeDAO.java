package com.bibekbazaz.bkb_employee_management_service.dao;

import com.bibekbazaz.bkb_employee_management_service.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDAO extends CrudRepository<Employee, Long> {
}

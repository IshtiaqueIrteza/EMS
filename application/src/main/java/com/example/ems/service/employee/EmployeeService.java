package com.example.ems.service.employee;

import com.example.ems.model.employee.Employee;
import org.json.simple.JSONObject;

public interface EmployeeService {
    JSONObject getEmployees();
    Employee getEmployee(int empId);
    int addNewEmployee(Employee employee); //return emp id
    int deleteEmployee(int empId);
}

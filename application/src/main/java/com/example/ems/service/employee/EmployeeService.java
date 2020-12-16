package com.example.ems.service.employee;

import com.example.ems.model.employee.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee getEmployee(int empId);
    int addNewEmployee(Employee employee); //return emp id
    int deleteEmployee(int empId);
}

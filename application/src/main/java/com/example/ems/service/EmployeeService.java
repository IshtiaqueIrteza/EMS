package com.example.ems.service;

import com.example.ems.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    Employee getEmployee(int empId);
    Employee addEmployee(Employee employee);
    int deleteEmployee(int empId);
}

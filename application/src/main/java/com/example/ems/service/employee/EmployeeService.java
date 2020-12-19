package com.example.ems.service.employee;

import com.example.ems.model.employee.Employee;
import org.json.simple.JSONObject;

import java.util.List;

public interface EmployeeService {
    JSONObject getEmployeesInfo();
    List<Employee> getEmployeeList();
    List<Integer> getEmployeeIds();
    Double totalSalary();
    Long employeeCount();
    JSONObject findAllByEmpId(List<Integer> empIds);
    JSONObject findAllByEmpIdNotIn(List<Integer> empIds);
    Employee getEmployee(int empId);
    int addNewEmployee(Employee employee); //return emp id
    int deleteEmployee(int empId);
}

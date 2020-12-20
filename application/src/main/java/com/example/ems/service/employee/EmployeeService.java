package com.example.ems.service.employee;

import com.example.ems.model.employee.Employee;
import com.example.ems.model.employee.EmployeeBankAccInfo;
import org.json.simple.JSONObject;

import java.util.List;

public interface EmployeeService {
    JSONObject getEmployeesInfo();
    List<Employee> getEmployeeList();
    List<Integer> getEmployeeIds();
    List<Object[]> employeeReport(); // report
    Double totalSalary();
    Long employeeCount();
    JSONObject findAllByEmpId(List<Integer> empIds);
    JSONObject findAllByEmpIdNotIn(List<Integer> empIds);
    Employee getEmployee(int empId);
    int addNewEmployee(Employee employee); // add or update //return emp id
    int updateEmployee(Employee employee); // update master
    int deleteEmployee(int empId);
}

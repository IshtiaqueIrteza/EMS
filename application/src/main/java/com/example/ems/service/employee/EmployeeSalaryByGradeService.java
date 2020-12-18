package com.example.ems.service.employee;

import com.example.ems.model.employee.EmployeeSalaryByGrade;

import java.util.List;
import java.util.Optional;

public interface EmployeeSalaryByGradeService {
    List<EmployeeSalaryByGrade> getList();
    Optional<EmployeeSalaryByGrade> getLowestGradeBasicSalary();
}

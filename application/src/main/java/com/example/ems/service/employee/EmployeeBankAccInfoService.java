package com.example.ems.service.employee;

import com.example.ems.model.employee.EmployeeBankAccInfo;

import java.util.List;

public interface EmployeeBankAccInfoService {
    char addEmployeeBankAccountInfo(EmployeeBankAccInfo employeeBankAccInfo);
    List<EmployeeBankAccInfo> employeeDetailList();
    EmployeeBankAccInfo getByEmpId(Integer empId);
    char updateEmployeeBankAccInfo(EmployeeBankAccInfo employeeBankAccInfo); // update detail
}

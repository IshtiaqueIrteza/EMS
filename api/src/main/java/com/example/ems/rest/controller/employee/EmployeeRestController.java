package com.example.ems.rest.controller.employee;

import com.example.ems.model.employee.Employee;
import com.example.ems.model.employee.EmployeeBankAccInfo;
import com.example.ems.service.employee.EmployeeBankAccInfoService;
import com.example.ems.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeBankAccInfoService employeeBankAccInfoService;

    @PostMapping("/employees")
    @ResponseBody
    public int addEmployeeBasicInfo(@RequestBody Employee employee){
        return this.employeeService.addNewEmployee(employee);
    }

    @PostMapping("/employees/{employeeId}/details")
    @ResponseBody
    public char addEmployeeBankAccountInfo(@PathVariable Integer employeeId, @RequestBody EmployeeBankAccInfo employeeBankAccInfo){
        employeeBankAccInfo.setEmpId(employeeId);
        return this.employeeBankAccInfoService.addEmployeeBankAccountInfo(employeeBankAccInfo);
    }
}

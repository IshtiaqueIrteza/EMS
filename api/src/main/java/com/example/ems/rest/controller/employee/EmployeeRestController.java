package com.example.ems.rest.controller.employee;

import com.example.ems.model.employee.Employee;
import com.example.ems.model.employee.EmployeeBankAccInfo;
import com.example.ems.service.employee.EmployeeBankAccInfoService;
import com.example.ems.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeBankAccInfoService employeeBankAccInfoService;

    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getAllEmployeeInfo(){
        return this.employeeService.getEmployeeList();
    }

    @GetMapping("/employees/{employeeId}")
    @ResponseBody
    public Employee getEmployeeInfo(@PathVariable Integer employeeId){
        return this.employeeService.getEmployee(employeeId);
    }

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

    @PutMapping("/employees/{employeeId}")
    @ResponseBody
    public int updateEmployeeMaster(@PathVariable Integer employeeId,@RequestBody Employee employee){
        employee.setEmpId(employeeId);
        return this.employeeService.updateEmployee(employee); //update mst
    }

    @PutMapping("/employees/{employeeId}/details")
    @ResponseBody
    public char updateEmployeeDetail(@PathVariable Integer employeeId, @RequestBody EmployeeBankAccInfo employeeBankAccInfo){
        employeeBankAccInfo.setEmpId(employeeId);
        return this.employeeBankAccInfoService.updateEmployeeBankAccInfo(employeeBankAccInfo);
    }

    @DeleteMapping("/employees/{employeeId}")
    @ResponseBody
    public int deleteEmployee(@PathVariable Integer employeeId){
        return this.employeeService.deleteEmployee(employeeId);
    }
}

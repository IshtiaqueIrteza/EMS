package com.example.ems.service.employee.impl;

import com.example.ems.model.employee.Employee;
import com.example.ems.repository.employee.EmployeeRepository;
import com.example.ems.service.employee.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private JSONObject calculateEmployeesInfo(List<Employee> employees){
        int empAggregateSalary = 0;
        int employeeCount = 0;

        for (Employee employee : employees) {
            empAggregateSalary += employee.getEmployeeSalaryByGrade().getSalary();
            employeeCount++;
        }

        JSONObject obj = new JSONObject();
        obj.put("totalEmployee", employeeCount);
        obj.put("empAggregateSalary", empAggregateSalary);
        obj.put("employeeList", employees);

        return obj;
    }

    @Override
    public JSONObject getEmployeesInfo() {
        return calculateEmployeesInfo(this.employeeRepository.findAll());
    }

    @Override
    public List<Employee> getEmployeeList() {
        return this.employeeRepository.findAll();
    }

    @Override
    public JSONObject findAllByEmpId(List<Integer> empIds) {
        return calculateEmployeesInfo(this.employeeRepository.findAllByEmpIdIn(empIds));
    }

    @Override
    public JSONObject findAllByEmpIdNotIn(List<Integer> empIds) {
        return calculateEmployeesInfo(this.employeeRepository.findAllByEmpIdNotIn(empIds));
    }

    @Override
    public Employee getEmployee(int empId) {
        return this.employeeRepository.findByEmpId(empId);
    }

    @Override
    public int addNewEmployee(Employee employee) {
        try{
            Integer maxEmpId = this.employeeRepository.getMaxEmpId();
            employee.setEmpId(maxEmpId == null ? 1001 : maxEmpId + 1);
            return this.employeeRepository.save(employee).getEmpId(); //return emp id
        }
        catch (Exception ex){
            return 0;
        }
    }

    @Override
    public int deleteEmployee(int empId) {
        return this.employeeRepository.deleteByEmpId(empId);
    }
}

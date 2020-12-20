package com.example.ems.service.employee.impl;

import com.example.ems.model.employee.Employee;
import com.example.ems.repository.employee.EmployeeBankAccInfoRepository;
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

    @Autowired
    EmployeeBankAccInfoRepository employeeBankAccInfoRepository;

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
    public List<Integer> getEmployeeIds() {
        return this.employeeRepository.getEmployeeIds();
    }

    @Override
    public List<Object[]> employeeReport() {
        return this.employeeRepository.employeeReport();
    }

    @Override
    public Double totalSalary() {
        return this.employeeRepository.totalSalary();
    }

    @Override
    public Long employeeCount() {
        return this.employeeRepository.count();
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
    public int updateEmployee(Employee employee) {
        try{
            Employee employeeToUpdate = this.employeeRepository.findByEmpId(employee.getEmpId());
            employeeToUpdate.setEmpName(employee.getEmpName());
            employeeToUpdate.setGrade(employee.getGrade());
            employeeToUpdate.setAddress(employee.getAddress());
            employeeToUpdate.setMobile(employee.getMobile());
            return this.employeeRepository.save(employeeToUpdate).getEmpId();
        }
        catch (Exception ex){
            return 0;
        }
    }

    @Override
    public int deleteEmployee(int empId) {
        try{
            this.employeeBankAccInfoRepository.deleteByEmpId(empId);
            this.employeeRepository.deleteByEmpId(empId);
            return 1;
        }
        catch (Exception ex){
            return 0;
        }
    }
}

package com.example.ems.service.employee.impl;

import com.example.ems.model.employee.EmployeeSalaryByGrade;
import com.example.ems.repository.employee.EmployeeSalaryByGradeRepository;
import com.example.ems.service.employee.EmployeeSalaryByGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeSalaryByGradeServiceImpl implements EmployeeSalaryByGradeService {

    @Autowired
    EmployeeSalaryByGradeRepository employeeSalaryByGradeRepository;

    @Override
    public List<EmployeeSalaryByGrade> getList() {
        return this.employeeSalaryByGradeRepository.findAll();
    }

    @Override
    public Optional<EmployeeSalaryByGrade> getLowestGradeBasicSalary() {
        return this.employeeSalaryByGradeRepository.findById('6'); //lowest grade is 6
    }
}

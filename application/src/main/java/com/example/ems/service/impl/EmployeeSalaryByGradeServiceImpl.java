package com.example.ems.service.impl;

import com.example.ems.model.EmployeeSalaryByGrade;
import com.example.ems.repository.EmployeeSalaryByGradeRepository;
import com.example.ems.service.EmployeeSalaryByGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeSalaryByGradeServiceImpl implements EmployeeSalaryByGradeService {

    @Autowired
    EmployeeSalaryByGradeRepository employeeSalaryByGradeRepository;

    @Override
    public List<EmployeeSalaryByGrade> getList() {
        return this.employeeSalaryByGradeRepository.findAll();
    }
}

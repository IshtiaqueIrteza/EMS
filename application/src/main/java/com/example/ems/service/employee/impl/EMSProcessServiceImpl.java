package com.example.ems.service.employee.impl;

import com.example.ems.repository.employee.EmployeeProcedureRepository;
import com.example.ems.service.employee.EMSProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EMSProcessServiceImpl implements EMSProcessService {

    @Autowired
    EmployeeProcedureRepository employeeProcedureRepo;

    @Override
    public String processInitialSalary(int lowestGradeBasic) {
        return this.employeeProcedureRepo.processInitialSalary(lowestGradeBasic);
    }
}

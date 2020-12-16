package com.example.ems.service.impl;

import com.example.ems.repository.EmployeeProcedureRepository;
import com.example.ems.service.EMSProcessService;
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

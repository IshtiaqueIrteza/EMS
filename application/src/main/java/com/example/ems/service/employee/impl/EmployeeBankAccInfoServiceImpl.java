package com.example.ems.service.employee.impl;

import com.example.ems.model.employee.EmployeeBankAccInfo;
import com.example.ems.repository.employee.EmployeeBankAccInfoRepository;
import com.example.ems.service.employee.EmployeeBankAccInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeBankAccInfoServiceImpl implements EmployeeBankAccInfoService {

    @Autowired
    EmployeeBankAccInfoRepository employeeBankAccInfoRepository;

    @Override
    public char addEmployeeBankAccountInfo(EmployeeBankAccInfo employeeBankAccInfo) {
        try{
            this.employeeBankAccInfoRepository.save(employeeBankAccInfo);
            return '1';
        }
        catch (Exception ex){
            return '0';
        }
    }
}

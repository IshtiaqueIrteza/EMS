package com.example.ems.service.employee.impl;

import com.example.ems.model.employee.EmployeeBankAccInfo;
import com.example.ems.repository.employee.EmployeeBankAccInfoRepository;
import com.example.ems.service.employee.EmployeeBankAccInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<EmployeeBankAccInfo> employeeDetailList() {
        return this.employeeBankAccInfoRepository.findAll();
    }

    @Override
    public EmployeeBankAccInfo getByEmpId(Integer empId) {
        return this.employeeBankAccInfoRepository.findByEmpId(empId);
    }

    @Override
    public char updateEmployeeBankAccInfo(EmployeeBankAccInfo employeeBankAccInfo) {
        try{
            EmployeeBankAccInfo employeeDtlToUpdate = this.employeeBankAccInfoRepository.findByEmpId(employeeBankAccInfo.getEmpId());
            employeeDtlToUpdate.setBankName(employeeBankAccInfo.getBankName());
            employeeDtlToUpdate.setBranchName(employeeBankAccInfo.getBranchName());
            employeeDtlToUpdate.setAccName(employeeBankAccInfo.getAccName());
            employeeDtlToUpdate.setAccNum(employeeBankAccInfo.getAccNum());
            employeeDtlToUpdate.setAccType(employeeBankAccInfo.getAccType());
            employeeDtlToUpdate.setCurBalance(employeeBankAccInfo.getCurBalance());
            this.employeeBankAccInfoRepository.save(employeeDtlToUpdate);
            return '1';
        }
        catch (Exception ex){
            return '0';
        }
    }
}

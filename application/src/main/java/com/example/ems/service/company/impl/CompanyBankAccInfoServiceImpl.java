package com.example.ems.service.company.impl;

import com.example.ems.model.company.CompanyBankAccInfo;
import com.example.ems.repository.company.CompanyBankAccInfoRepository;
import com.example.ems.service.company.CompanyBankAccInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyBankAccInfoServiceImpl implements CompanyBankAccInfoService {

    @Autowired
    CompanyBankAccInfoRepository companyBankAccInfoRepository;

    @Override
    public int updateAccountBalance(double balanceToCredit) {
        try{
            return this.companyBankAccInfoRepository.updateAccountBalance(balanceToCredit);
        }
        catch (Exception ex){
            return 0;
        }
    }

    @Override
    public CompanyBankAccInfo getAccountInfo() {
        return this.companyBankAccInfoRepository.getOne(1L); //hardcoded
    }
}

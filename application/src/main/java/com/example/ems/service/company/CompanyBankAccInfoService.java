package com.example.ems.service.company;

import com.example.ems.model.company.CompanyBankAccInfo;

public interface CompanyBankAccInfoService {
    int updateAccountBalance(double balanceToCredit); //only one account
    CompanyBankAccInfo getAccountInfo();
}

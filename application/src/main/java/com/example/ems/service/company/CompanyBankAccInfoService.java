package com.example.ems.service.company;

import com.example.ems.model.company.CompanyBankAccInfo;
import org.json.simple.JSONObject;

public interface CompanyBankAccInfoService {
    int updateAccountBalance(double balanceToCredit); //only one account
    CompanyBankAccInfo getAccountInfo();
//    char salaryDisburseProcess(); //write procedure later
    JSONObject salaryDisburseProcess(String empIdsStr); //comma separated for SP
}

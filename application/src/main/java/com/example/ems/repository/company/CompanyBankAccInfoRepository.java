package com.example.ems.repository.company;

import com.example.ems.model.company.CompanyBankAccInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBankAccInfoRepository extends JpaRepository<CompanyBankAccInfo, Long> {
    @Modifying
    @Query(value = "update company_bank_acc_info set cur_bal = cur_bal + ?1 where acc_id = 1", nativeQuery = true)
    int updateAccountBalance(double balanceToCredit); //only one account

    /*@Modifying
    @Query(value = "update company_bank_acc_info set cur_bal = cur_bal - ?1 where acc_id = 1", nativeQuery = true)
    int debitCompanyAccountBalance(double employeeSalary); //debit employee salary from main account

    @Modifying
    @Query(value = "update emp_bank_acc_info set cur_bal = cur_bal + ?1 where emp_id = ?2", nativeQuery = true)
    int creditEmployeeAccountBalance(double employeeSalary, Integer empId); //credit employee salary account*/
}

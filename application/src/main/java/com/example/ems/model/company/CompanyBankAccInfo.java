package com.example.ems.model.company;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "company_bank_acc_info")
public class CompanyBankAccInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACC_ID")
    private Long accId;

    @Column(name = "ACC_NAME")
    private String accName;

    @Column(name = "ACC_NUM")
    private int accNum;

    @Column(name = "ACC_TYPE")
    private char accType;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BRANCH_NAME")
    private String branchName;

    @Column(name = "CUR_BAL")
    private double curBalance;

}
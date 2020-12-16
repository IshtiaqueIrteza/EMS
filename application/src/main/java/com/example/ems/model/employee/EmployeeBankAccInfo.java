package com.example.ems.model.employee;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "emp_bank_acc_info")
public class EmployeeBankAccInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

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

    @Column(name = "EMP_ID")
    private Integer empId;

}
package com.example.ems.model;

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

    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "", referencedColumnName = "", foreignKey = "")
    private Employee employee;*/

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
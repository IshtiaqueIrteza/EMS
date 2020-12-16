package com.example.ems.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMP_ID")
    private int empId;

    @Column(name = "EMP_NAME")
    private String empName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GRADE", referencedColumnName = "GRADE_ID", insertable = false, updatable = false)
    private EmployeeSalaryByGrade salary;

    @Column(name = "GRADE")
    private char grade;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "MOBILE")
    private String mobile;

}

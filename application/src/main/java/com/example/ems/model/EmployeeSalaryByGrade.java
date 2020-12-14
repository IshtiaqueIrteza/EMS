package com.example.ems.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee_salary_by_grade")
public class EmployeeSalaryByGrade {

    @Id
    @Column(name = "GRADE_ID")
    private char grade;

    @Column(name = "SALARY")
    private double salary;

}
package com.example.ems.repository;

import com.example.ems.model.EmployeeSalaryByGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSalaryByGradeRepository extends JpaRepository<EmployeeSalaryByGrade, String> {
}

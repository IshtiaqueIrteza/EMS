package com.example.ems.repository.employee;

import com.example.ems.model.employee.EmployeeBankAccInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeBankAccInfoRepository extends JpaRepository<EmployeeBankAccInfo, Long> {
}

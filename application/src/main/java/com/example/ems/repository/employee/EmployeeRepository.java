package com.example.ems.repository.employee;

import com.example.ems.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmpId(int empId);
    int deleteByEmpId(int empId);
    @Query(value = "select max(EMP_ID) from employee", nativeQuery = true)
    Integer getMaxEmpId();
}

package com.example.ems.repository.employee;

import com.example.ems.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmpId(int empId);
    List<Employee> findAllByEmpIdIn(List<Integer> empIds);
    List<Employee> findAllByEmpIdNotIn(List<Integer> empIds);
    int deleteByEmpId(int empId);
    @Query(value = "select max(EMP_ID) from employee", nativeQuery = true)
    Integer getMaxEmpId();
}

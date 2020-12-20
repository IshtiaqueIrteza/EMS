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
    @Query(value = "select EMP_ID from employee", nativeQuery = true)
    List<Integer> getEmployeeIds();
    @Query(value = "select sum(b.SALARY) from employee a\n" +
            "inner join employee_salary_by_grade b on a.GRADE = b.GRADE_ID", nativeQuery = true)
    Double totalSalary();
    @Query(value = "select a.EMP_NAME, b.GRADE_ID, b.SALARY, c.CUR_BAL from employee a\n" +
            "inner join employee_salary_by_grade b on a.GRADE = b.GRADE_ID\n" +
            "inner join emp_bank_acc_info c on a.EMP_ID = c.EMP_ID", nativeQuery = true)
    List<Object[]> employeeReport();
}

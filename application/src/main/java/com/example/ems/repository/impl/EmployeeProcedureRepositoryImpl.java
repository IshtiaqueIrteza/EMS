package com.example.ems.repository.impl;

import com.example.ems.repository.EmployeeProcedureRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EmployeeProcedureRepositoryImpl implements EmployeeProcedureRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public String processInitialSalary(int lowestGradeBasic) {

        StoredProcedureQuery query = em.createStoredProcedureQuery("calculate_salary_by_grade")
                .registerStoredProcedureParameter(1, int.class, ParameterMode.IN);

        query.setParameter(1, lowestGradeBasic);

        query.execute();

        String result = query.getSingleResult().toString();

        return result.equals("1") ? "Salary calculation processed successfully" : "Something went wrong ! Please try again later";
    }
}

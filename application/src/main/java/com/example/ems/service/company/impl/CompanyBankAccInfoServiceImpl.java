package com.example.ems.service.company.impl;

import com.example.ems.model.company.CompanyBankAccInfo;
import com.example.ems.repository.company.CompanyBankAccInfoRepository;
import com.example.ems.service.company.CompanyBankAccInfoService;
import com.example.ems.service.employee.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyBankAccInfoServiceImpl implements CompanyBankAccInfoService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CompanyBankAccInfoRepository companyBankAccInfoRepository;

    @Autowired
    EmployeeService employeeService;

    @Override
    public int updateAccountBalance(double balanceToCredit) {
        try{
            return this.companyBankAccInfoRepository.updateAccountBalance(balanceToCredit);
        }
        catch (Exception ex){
            return 0;
        }
    }

    @Override
    public CompanyBankAccInfo getAccountInfo() {
        return this.companyBankAccInfoRepository.getOne(1L); //hardcoded
    }

    /*@Override
    public char salaryDisburseProcess() { //write procedure later

        List<Employee> employeeList = this.employeeService.getEmployeeList();

        try{

            for (Employee employee: employeeList) {

                if(this.getAccountInfo().getCurBalance() > 0){
                    //debit current employee salary from company's main account
                    this.companyBankAccInfoRepository.
                            debitCompanyAccountBalance(employee.getEmployeeSalaryByGrade().getSalary());
                    //credit employee account by salary
                    this.companyBankAccInfoRepository.
                            creditEmployeeAccountBalance(employee.getEmployeeSalaryByGrade().getSalary(), employee.getEmpId());
                }
                else
                {
                    // return info
                }
            }

            return '1'; //success
        }
        catch (Exception ex){
            return '2';
        }
    }*/

    @Override
    public JSONObject salaryDisburseProcess() {

        StoredProcedureQuery query = em.createStoredProcedureQuery("salary_disburse_process");
        query.execute();

        String res = query.getSingleResult().toString();

        JSONObject obj = new JSONObject();

        if(res.equals("0")){ //error
            obj.put("msg", "0");
        }
        else if(res.equals("1")){ //success all
            obj = employeeService.getEmployeesInfo();
            obj.put("msg", "1");
        }
        else{ //success partial

            String[] empIds = res.split(",");

            if(empIds.length != 0){
                //some employees gets salary

                List<Integer> employeeIds = new ArrayList<>();

                for (String empId: empIds) {
                    employeeIds.add(Integer.parseInt(empId));
                }

                obj = employeeService.findAllByEmpId(employeeIds);
                obj.put("msg", "2");
                obj.put("unpaidEmployees", employeeService.findAllByEmpIdNotIn(employeeIds));
            }
            else
            {
                //company balance is too short, none of the employees gets salary
                obj = employeeService.getEmployeesInfo();
                obj.put("msg", "3");
            }
        }

        return obj;
    }

}

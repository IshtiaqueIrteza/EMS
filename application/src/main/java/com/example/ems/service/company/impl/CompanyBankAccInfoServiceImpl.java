package com.example.ems.service.company.impl;

import com.example.ems.model.company.CompanyBankAccInfo;
import com.example.ems.model.employee.Employee;
import com.example.ems.repository.company.CompanyBankAccInfoRepository;
import com.example.ems.service.company.CompanyBankAccInfoService;
import com.example.ems.service.employee.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpSession;
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

    @Autowired
    HttpSession session;

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

    public StringBuilder buildEmployeePaymentStatusTable(List<Employee> employees, boolean status){
        StringBuilder s = new StringBuilder();

        for (Employee employee: employees) {
            s.append("<tr>");
            s.append("<td>" + employee.getEmpId() + "</td>");
            s.append("<td>" + employee.getEmpName() + "</td>");
            s.append("<td>" + employee.getEmployeeSalaryByGrade().getSalary() + "</td>");
            s.append(status == true ? "<td>Paid</td></tr>" : "<td>Unpaid</td></tr>");
        }

        return s;
    }

    public String generateCommaSeparatedEmpIds(List<Integer> empIds, boolean status){

        StringBuilder s = new StringBuilder();

        for (Integer empId: empIds) {
            s.append(empId.toString()+",");
        }

        if(s.length() >= 1)
            s.deleteCharAt(s.length() - 1);

        StringBuilder employeeIds = new StringBuilder();

        employeeIds.append(status == true ? (String) session.getAttribute("paidEmpIds") :
                (String) session.getAttribute("unpaidEmpIds"));

        return s.toString();
    }

    @Override
    public JSONObject salaryDisburseProcess(String empIdsStr) { //comma separated empIds for SP

        if(empIdsStr.equals(""))
            empIdsStr = generateCommaSeparatedEmpIds(this.employeeService.getEmployeeIds(), false); //initial request

        StoredProcedureQuery query = em.createStoredProcedureQuery("salary_disburse_process")
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                .setParameter(1, empIdsStr);

        query.execute();

        String res = query.getSingleResult().toString();

        JSONObject obj = new JSONObject();

        if(res.equals("0")){ //error
            obj.put("msg", "0");
        }
        else if(res.equals("1")){ //success all
            obj = employeeService.getEmployeesInfo();
            obj.put("msg", "1");
            session.removeAttribute("paidEmpIds");
            session.removeAttribute("unpaidEmpIds");
        }
        else{ //success partial

            //some employees gets salary

            // 1st: check session for any previous paid empIds

            String previousPaidEmpIdsFromSession = session.getAttribute("paidEmpIds") == null ? ""
                    : session.getAttribute("paidEmpIds").toString();
            String[] previousPaidEmpIdsArr = previousPaidEmpIdsFromSession.split(",");

            // merge with any previously paid empIds
            List<Integer> totalPaidEmpIds = new ArrayList<>();

            // check if any paid empIds exists 1st
            if(!previousPaidEmpIdsFromSession.equals("")){
                // get [previous] paidEmpIds from session
                for (String empId: previousPaidEmpIdsArr) {
                    totalPaidEmpIds.add(Integer.parseInt(empId));
                }
            }

            // 2nd: check newly paid empIds, returned from proc

            // get returned [recent] paidEmpIds from proc
            String[] newlyPaidEmpIds = res.split(",");

            // merge with any previously paid empIds
            if(!newlyPaidEmpIds[0].equals("")){
                for (String empId: newlyPaidEmpIds) {
                    totalPaidEmpIds.add(Integer.parseInt(empId));
                }
            }

            if(totalPaidEmpIds.size() == 0){
                //insufficient balance
                obj.put("totalEmployee", 0);
                obj.put("empAggregateSalary", 0);

                JSONObject object = new JSONObject();

                object.put("totalEmployee", this.employeeService.employeeCount());
                object.put("empAggregateSalary", this.employeeService.totalSalary());

                obj.put("unpaidEmpInfo", object);
            }
            else{
                //paid employees
                obj = employeeService.findAllByEmpId(totalPaidEmpIds);
                List<Employee> employees = (List<Employee>) employeeService.findAllByEmpId(totalPaidEmpIds).get("employeeList"); //paid employees

                obj.remove("employeeList");
                obj.put("tableHTMLPaid", buildEmployeePaymentStatusTable(employees, true));

                //set paidEmpIds in session
                session.setAttribute("paidEmpIds", generateCommaSeparatedEmpIds(totalPaidEmpIds, true));

                //unpaid employees
                JSONObject newObj;
                newObj = employeeService.findAllByEmpIdNotIn(totalPaidEmpIds);

//                obj.put("unpaidEmployees", employeeService.findAllByEmpIdNotIn(employeeIds)); //unpaid employees

                employees = (List<Employee>) newObj.get("employeeList"); //unpaid employees

                List<Integer> unpaidEmpIds = new ArrayList<>();

                for (Employee employee: employees) {
                    unpaidEmpIds.add(employee.getEmpId());
                }

                newObj.remove("employeeList");
                obj.put("tableHTMLUnpaid", buildEmployeePaymentStatusTable(employees, false));

                obj.put("unpaidEmpInfo", newObj);

                //set unpaidEmpIds in session
                session.setAttribute("unpaidEmpIds", generateCommaSeparatedEmpIds(unpaidEmpIds, false));
            }

            obj.put("companyCurBal", getAccountInfo().getCurBalance());
            obj.put("msg", "2");

        }

        return obj;
    }

}

package com.example.ems.controller.company;

import com.example.ems.service.company.CompanyBankAccInfoService;
import com.example.ems.service.employee.EmployeeService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/company-utility")
public class CompanyUtilityController {

    @Autowired
    CompanyBankAccInfoService companyBankAccInfoService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/bank-acc-info")
    public String displayCompanyBankBalanceInfo(Model model){
        model.addAttribute("companyBankInfo", this.companyBankAccInfoService.getAccountInfo());
        return "/company/company-bank-acc-info";
    }

    @PostMapping("/update-company-acc-bal")
    public String updateCompanyAccountBalance(@RequestParam("bal-to-credit") double balanceToCredit,
                                              RedirectAttributes redirectAttributes){
        int rowUpdated = this.companyBankAccInfoService.updateAccountBalance(balanceToCredit);
        if(rowUpdated == 1)
            redirectAttributes.addFlashAttribute("message", 1);
        else
            redirectAttributes.addFlashAttribute("message", 0);

        return "redirect:/company-utility/bank-acc-info";
    }

    @GetMapping("/salary-disburse")
    public String salaryDisbursementProcessForm(Model model){
        JSONObject obj = this.employeeService.getEmployees();
        model.addAttribute("totalEmployee", obj.get("totalEmployee"));
        model.addAttribute("employees", obj.get("employeeList"));
        model.addAttribute("empAggregateSalary", obj.get("empAggregateSalary"));
        model.addAttribute("companyAccBalance", this.companyBankAccInfoService.getAccountInfo().getCurBalance());
        return "/company/salary-disburse-process-form";
    }
}

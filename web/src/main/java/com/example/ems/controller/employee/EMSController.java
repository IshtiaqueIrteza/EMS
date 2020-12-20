package com.example.ems.controller.employee;

import com.example.ems.model.employee.EmployeeSalaryByGrade;
import com.example.ems.service.employee.EMSProcessService;
import com.example.ems.service.employee.EmployeeBankAccInfoService;
import com.example.ems.service.employee.EmployeeSalaryByGradeService;
import com.example.ems.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EMSController {

    @Autowired
    EMSProcessService emsProcessService;

    @Autowired
    EmployeeSalaryByGradeService employeeSalaryByGradeService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeBankAccInfoService employeeBankAccInfoService;

    @GetMapping("/init-sal-calc")
    public String initialSalaryCalculation(){
        return "/employee/init-sal-calc-form";
    }

    @GetMapping("/get-lowest-grade-sal-basic")
    @ResponseBody
    public Optional<EmployeeSalaryByGrade> getLowestGradeBasicSalaryIfExists(){
        return this.employeeSalaryByGradeService.getLowestGradeBasicSalary();
    }

    @PostMapping("/init-sal-calc")
    @ResponseBody
    public String initialSalaryCalculationProcess(@RequestParam("basic") int lowestGradeBasic){
        return this.emsProcessService.processInitialSalary(lowestGradeBasic);
    }

    @GetMapping("/grade-salary-sheet")
    public String employeeSalarySheetByGrade(Model model){
        List<EmployeeSalaryByGrade> list = this.employeeSalaryByGradeService.getList();
        model.addAttribute("list", list);
        return "/employee/salary-sheet-by-grade";
    }

    @GetMapping("/emp-form")
    public String employeeForm(){
        return "/employee/emp-form";
    }

    @GetMapping("/emp-salary-sheet")
    public String employeeSalarySheet(Model model){
        model.addAttribute("employees", this.employeeService.getEmployeesInfo().get("employeeList"));
        return "/employee/emp-salary-sheet";
    }

    @GetMapping("/emp-acc-balance")
    public String employeeBankAccInfo(Model model){
        model.addAttribute("employees", this.employeeService.employeeReport());
        return "/employee/emp-acc-balance-info";
    }

    @GetMapping("/list")
    public String employeeCRUDForm(Model model){
        model.addAttribute("employees", this.employeeService.getEmployeeList());
        return "/employee/list";
    }

    @GetMapping("/edit/{employeeId}")
    public String employeeEditForm(@PathVariable Integer employeeId, Model model){
        model.addAttribute("employees", this.employeeService.getEmployee(employeeId));
        model.addAttribute("employeeDetails", this.employeeBankAccInfoService.getByEmpId(employeeId));
        return "/employee/edit";
    }

}

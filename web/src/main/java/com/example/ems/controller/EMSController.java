package com.example.ems.controller;

import com.example.ems.model.EmployeeSalaryByGrade;
import com.example.ems.service.EmployeeSalaryByGradeService;
import com.example.ems.service.EMSProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EMSController {

    @Autowired
    EMSProcessService employeeService;

    @Autowired
    EmployeeSalaryByGradeService employeeSalaryByGradeService;

    @GetMapping("/init-sal-calc")
    public String initialSalaryCalculation(){
        return "/employee/init-sal-calc-form";
    }

    @PostMapping("/init-sal-calc")
    @ResponseBody
    public String initialSalaryCalculationProcess(@RequestParam("basic") int lowestGradeBasic){
        return employeeService.processInitialSalary(lowestGradeBasic);
    }

    @GetMapping("/grade-salary-sheet")
    public String employeeSalarySheet(Model model){
        List<EmployeeSalaryByGrade> list = this.employeeSalaryByGradeService.getList();
        model.addAttribute("list", list);
        return "/employee/salary-sheet-by-grade";
    }

    @GetMapping("/emp-form")
    public String employeeForm(){
        return "/employee/emp-form";
    }

}

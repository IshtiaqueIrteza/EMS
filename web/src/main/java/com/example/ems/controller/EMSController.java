package com.example.ems.controller;

import com.example.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EMSController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/init-sal-calc")
    public String initialSalaryCalculation(){
        return "/employee/init-sal-calc-form";
    }

    @PostMapping("/init-sal-calc")
    public String initialSalaryCalculationProcess(@RequestParam("lowest_grade_basic") int lowestGradeBasic,
                                                  RedirectAttributes redirectAttributes, BindingResult bindingResult){
        String res = employeeService.processInitialSalary(lowestGradeBasic);
        if(res.equals('1')){
            redirectAttributes.addFlashAttribute("msg", "Salary calculation processed successfully");
            return "redirect:/employee/salary-sheet";
        }
        else{
            redirectAttributes.addFlashAttribute("msg", "Salary calculation processed successfully");
            return "redirect:/employee/salary-sheet";
        }
    }

    @GetMapping("/salary-sheet")
    public String employeeSalarySheet(){
        return "/employee/init-sal-calc-form";
    }
}

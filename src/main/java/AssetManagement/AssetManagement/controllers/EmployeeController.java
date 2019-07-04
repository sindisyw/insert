/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.Status;
import AssetManagement.AssetManagement.entities.LoaningRequest;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.LoanRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.AssetServices;
import AssetManagement.AssetManagement.services.DetailAssetServices;
import AssetManagement.AssetManagement.services.EmployeeServices;
import AssetManagement.AssetManagement.services.JobServices;
import AssetManagement.AssetManagement.services.LoanServices;
import AssetManagement.AssetManagement.services.RepairServices;
import AssetManagement.AssetManagement.services.RoleServices;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author HP
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private LoanServices loanServices;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private RepairServices repairServices;
    @Autowired
    private AssetServices assetServices;
    @Autowired
    private DetailAssetServices detailassetServices;

    @GetMapping("/employee")
    public String manager(Model model) {
        return "dashboard/employee";
    }

    @GetMapping("/emp_loaning")
    public String approvalRequest(Model model) {
        model.addAttribute("dataEmp", employeeServices.findAll());
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataAsset", assetServices.findAll());
        model.addAttribute("detailAsset", detailassetServices.findAll());
        return "employee/loaning";
    }

    @PostMapping("/loaningrequest/addData")
    public String addLoan(LoaningRequest loan) {
        loan.setId("0");
        Status status = new Status();
        status.setId("ST1");
        loan.setStatus(status);
        loanRepository.save(loan);
        return "redirect:/emp_loaning";
    }
}

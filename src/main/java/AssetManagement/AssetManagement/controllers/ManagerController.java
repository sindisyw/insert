/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.AssetServices;
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
public class ManagerController {

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
    private RepairServices repairServices;
    @Autowired
    private AssetServices assetServices;

   

    @GetMapping("/manager")
    public String manager(Model model) {
        return "dashboard/manager";
    }
    @GetMapping("/manager_approval-request")
    public String approvalRequest(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        return "manager/approval_request";
    }
    @GetMapping("/manager_approval-history")
    public String approvalHistory(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        return "manager/approval_history";
    }
    @GetMapping("/manager_repair")
    public String repair(Model model) {
        model.addAttribute("dataRepair", repairServices.findAll());
        return "manager/repair";
    }


}

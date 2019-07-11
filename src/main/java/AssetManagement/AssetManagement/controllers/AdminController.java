/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.Job;
import AssetManagement.AssetManagement.repository.AccountRepository;
import AssetManagement.AssetManagement.repository.JobRepository;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.RoleRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.AssetServices;
import AssetManagement.AssetManagement.services.EmployeeServices;
import AssetManagement.AssetManagement.services.JobServices;
import AssetManagement.AssetManagement.services.LoanServices;
import AssetManagement.AssetManagement.services.RepairServices;
import AssetManagement.AssetManagement.services.RoleServices;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HP
 */
@Controller
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LoanServices loanServices;
    @Autowired
    private RepairServices repairServices;
    @Autowired
    private AssetServices assetServices;

    @GetMapping("/admin")
    public String admin(Model model) {
        return "dashboard/admin";
    }

    @GetMapping("/admin-employee")
    public String adm_employee(Model model) {
        model.addAttribute("dataEmp", employeeRepository.getAll());
        model.addAttribute("dataAcc", accountServices.findAll());
        model.addAttribute("dataRole", roleServices.findAll());
        return "admin/employee";
    }

    @GetMapping("/admin-job&role")
    public String job(Model model) {
        model.addAttribute("dataJob", jobServices.findAll());
        model.addAttribute("dataRole", roleServices.findAll());
        return "admin/job";
    }

    @GetMapping("/admin-asset")
    public String asset(Model model) {
        model.addAttribute("dataAsset", assetServices.findAll());
        return "admin/asset";
    }

    @PostMapping("/updateData")
    public String updateData(Employee employee) {
        employee.setId("0");
        employee.setIsDelete("false");
        
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/findEmp")
    @ResponseBody
    public Employee findOne(String id) {
        Employee e = new Employee(employeeRepository.getEmployeeById(id).get(0).getId(),
                employeeRepository.getEmployeeById(id).get(0).getFirstName(),
                employeeRepository.getEmployeeById(id).get(0).getLastName(),
                employeeRepository.getEmployeeById(id).get(0).getEmail(),
                employeeRepository.getEmployeeById(id).get(0).getSalary(),
                employeeRepository.getEmployeeById(id).get(0).getPhoneNumber(),
                employeeRepository.getEmployeeById(id).get(0).getManager().getId()
        );
        return e;
    }

}

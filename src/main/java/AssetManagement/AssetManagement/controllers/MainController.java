/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.config.EmailConfig;
import AssetManagement.AssetManagement.entities.Account;
import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.EmployeeRole;
import AssetManagement.AssetManagement.entities.Role;
import AssetManagement.AssetManagement.repository.AccountRepository;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.EmployeeRoleRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.AssetServices;
import AssetManagement.AssetManagement.services.EmployeeServices;
import AssetManagement.AssetManagement.services.JobServices;
import AssetManagement.AssetManagement.services.LoanServices;
import AssetManagement.AssetManagement.services.RepairServices;
import AssetManagement.AssetManagement.services.RoleServices;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;
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
    private EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    private LoanServices loanServices;
    @Autowired
    private RepairServices repairServices;
    @Autowired
    private AssetServices assetServices;
    @Autowired
    private EmailConfig emailConfig;

    @RequestMapping(value = {"/", "", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String index(Model model) {
        return "dashboard/home";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }
    @GetMapping("/request")
    public String loaning(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataRepair", repairServices.findAll());
        return "request";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataRepair", repairServices.findAll());
        return "history";
    }

    @PostMapping("/addData")
    public String addData(Employee employee) {
        employee.setId("0");
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/AccountActivation")
    public String aktivasi(@RequestParam("token") String token, @RequestParam(required = false) String ActivationCode, @RequestParam(defaultValue = "go") String success, Model model) {
        String result = "aktivasi/index";
        boolean error = true;
        if (success.equals("true")) {
            Account account = accountRepository.getAccByToken(ActivationCode);
//            System.out.println(account.getUsername());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("password", account.getPassword());
            result = "aktivasi/success";
        } else if (success == null || ActivationCode == null) {
            result = "aktivasi/index";
        } else if (ActivationCode.equals("false")) {
            error = false;
            result = "aktivasi/index";
        }

        model.addAttribute("token", token);
        model.addAttribute("error", error);
        return result;
    }

    @PostMapping("/AccountActivation")
    public String aktivasi_success(@RequestParam("token") String token, String aktivasi, Model model) {
        String err = "false";
        String result = "AccountActivation?token=" + token + "&ActivationCode=" + err;
        Account account = accountRepository.getAccByToken(aktivasi);
        
        if (account != null) {
            Employee employee = employeeServices.findById(account.getId()).get();
            Random rnd = new Random();
            String firstname = employee.getFirstName();
            String lastname = employee.getLastName();
            String[] splitted = firstname.split("\\s+");
            String[] splitted2 = lastname.split("\\s+");
            String[] word = {"Fabulous", "Pretty", "Dope", "Fearless", "Sonorius", "Rocking", "Champion"};
            String[] fullName = combine(splitted, splitted2);
            //System.out.println(Arrays.toString(fullName));
            String name = "";
            int idy = new Random().nextInt(fullName.length);
            name += fullName[idy];
            int idx = new Random().nextInt(word.length);
            name += "Is" + word[idx];
            name += Integer.toString(rnd.nextInt(99));
            String pass = "";
            int idpy = new Random().nextInt(fullName.length);
            pass += potong(fullName[idpy]);
            int idpx = new Random().nextInt(fullName.length);
            pass += potong(fullName[idpx]);
//            String newPass = genPass(pass);
//            account.setUsername(name);
//            account.setPassword(newPass);
//            accountRepository.save(account);
//            System.out.println(newPass);
            result = "AccountActivation?token=" + token + "&ActivationCode=" + aktivasi + "&success=true";
        }
        
        return "redirect:/" + result; 
    }
    
    public String potong(String kata){
        String result = "";
        if (kata.length() > 5) {
            result = kata.substring(0, 5);
            
        } else {
            result = kata;
        }
        
        return result;
    }
    
    public String genPass(String kalimatasli) {
        char[] wordA = {'@','4'};
        char[] wordE = {'3'};
        char[] wordS = {'$'};
        char[] wordO = {'0','#'};
        char[] wordI = {'1','!'};
        char[] huruf = new char[kalimatasli.length()];
        for (int i = 0; i < kalimatasli.length(); i++) {
            if (kalimatasli.charAt(i) == 'a' || kalimatasli.charAt(i) == 'A') {
                int id = new Random().nextInt(wordA.length);
                huruf[i] = wordA[id];
            } else if (kalimatasli.charAt(i) == 'i' || kalimatasli.charAt(i) == 'I') {
                int id = new Random().nextInt(wordI.length);
                huruf[i] = wordI[id];
            } else if (kalimatasli.charAt(i) == 'e' || kalimatasli.charAt(i) == 'E') {
                int id = new Random().nextInt(wordE.length);
                huruf[i] = wordE[id];
            } else if (kalimatasli.charAt(i) == 'o' || kalimatasli.charAt(i) == 'O') {
                int id = new Random().nextInt(wordO.length);
                huruf[i] = wordO[id];
            } else if (kalimatasli.charAt(i) == 's' || kalimatasli.charAt(i) == 'S') {
                int id = new Random().nextInt(wordS.length);
                huruf[i] = wordS[id];
            }else {
                huruf[i] = kalimatasli.charAt(i);
            }
        }
        String alay = new String(huruf);
        return alay;
    }

    public String[] combine(String[] a, String[] b) {
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    @GetMapping("/ConfigureAccount")
    public String success(@RequestParam("token") String token, Model model) {
        return "aktivasi/success";
    }

    @PostMapping("/addDataAcc")
    public String addAcc(String password, String id, String role, Account account, EmployeeRole employeeRole) {
        //account.setId("0");
        String hasil = "failed";
        String result = "redirect:/admin-employee";

        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        String actCode = generatedString.toUpperCase();

        if (!id.isEmpty()) {
            account.setIsDelete("false");
            account.setPassword(new BCryptPasswordEncoder().encode(password));
            account.setIsActive("false");
            account.setToken(actCode);
            employeeRole.setEmployee(new Employee(id));
            employeeRole.setRole(new Role(role));
            accountRepository.save(account);
            employeeRoleRepository.save(employeeRole);
            hasil = "success";
        }

        if (hasil.equals("success")) {
            Employee employee = employeeServices.findById(id).get();
            String subject = "Your Account Almost Ready";
            String token = UUID.randomUUID().toString();
            String email = employee.getEmail();
            String message = templateEmail(employee.getEmail(), token, actCode);

            if (emailConfig.sendEmail(email, subject, message)) {
                result = "redirect:/admin-employee";
            }
        }

        return result;
    }

    @GetMapping("/EmpController/softdelete/{id}")
    public String softDelete(@PathVariable("id") String id, Employee employee) {
        employee.setIsDelete("true");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    public String templateEmail(String name, String token, String actCode) {
        String link = "http://localhost:8088/AccountActivation?token=" + token;
        String host = "http://localhost:8088";
        String imgUrl = "https://www.metrodata.co.id/web/images/business/2014-08-20-112626.png";
        String template = "<div style=\"background:#fff; border:2px solid #cccccc; padding:40px\">\n"
                + "<p style=\"text-align:center\"><span style=\"font-size:14px\"><a href=\"" + host + "\" target=\"_blank\"><img alt=\"\" src=\"" + imgUrl + "\" style=\"height:200px; background:#fff\" /></a></span></p>\n"
                + "<h1><strong>Aktivasi Acccount</strong></h1>\n"
                + "<p>Hai " + name + ",</p>\n"
                + "<p>Akun anda untuk mengakses Asset Management hampir siap. Gunakan kode aktivasi berikut untuk mengaktivasi account anda dengan mengklik tombol dibawah :</p>\n"
                + "<center><h1><strong>" + actCode + "</strong></h1></center>\n"
                + "<center><a href=\"" + link + "\" target=\"_blank\"><h1 style=\"background:linear-gradient(#ff9900, #ffaf00); border-radius:5px; color:#ffffff; display:inline-block; padding:8px 20px\"><strong>Activate Account</strong></h1></a></center>\n"
                + "<p>&nbsp;</p>\n"
                + "<p>Terimakasih atas perhatiannya,</p>\n"
                + "<p><strong>Asset Management</strong></p>\n"
                + "<p>&nbsp;</p>\n"
                + "<p><strong>nb :&nbsp;</strong>apabila terjadi suatu kesalahan dimohon untuk menghubungi admin</p>\n"
                + "</div>";
        return template;
    }

}

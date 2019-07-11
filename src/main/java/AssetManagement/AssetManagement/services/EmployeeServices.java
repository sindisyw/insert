/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class EmployeeServices {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> findById(String var){
        return employeeRepository.findById(var);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;


import AssetManagement.AssetManagement.entities.LoaningRequest;
import AssetManagement.AssetManagement.repository.LoanRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class LoanServices {
    
    @Autowired
    private LoanRepository loanRepository;
    
    public Iterable<LoaningRequest> findAll() {
        return loanRepository.findAll();
    }
}

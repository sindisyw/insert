/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;

import AssetManagement.AssetManagement.entities.Account;
import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.AccountRepository;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class AccountServices implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

//    @Transactional
//    public User registerNewUserAccount(Account accountDto) 
//      throws EmailExistsException {
//         
//        if (emailExists(accountDto.getEmail())) {   
//            throw new EmailExistsException(
//              "There is an account with that email address:  + accountDto.getEmail());
//        }
//        User user = new User();    
//        user.setFirstName(accountDto.getFirstName());
//        user.setLastName(accountDto.getLastName());
//        user.setPassword(accountDto.getPassword());
//        user.setEmail(accountDto.getEmail());
//        user.setRoles(Arrays.asList("ROLE_USER"));
//        return repository.save(user);       
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: " + username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword().toLowerCase(), enabled, accountNonExpired,
//                credentialsNonExpired, accountNonLocked,
//                getAuthorities(user.getRoles()));
        return null;
    }
}

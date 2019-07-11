/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;

import AssetManagement.AssetManagement.entities.Account;
import AssetManagement.AssetManagement.entities.Employee;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    @Transactional
    @Modifying
    @Query(value = "SELECT a.id, a.username, a.password, a.is_delete, a.is_active, er.role FROM account a "
                 + "INNER JOIN employee_role er "
                 + "ON a.id = er.employee "
                 + "WHERE a.username = :username", nativeQuery = true)
    Account findByUsername(@Param("username") String username);
    
    @Query(value = "SELECT e.email FROM employee e INNER JOIN account a ON e.id=a.id WHERE is_active='false' AND a.token= :token LIMIT 1", nativeQuery = true)
    List getEmailByToken(@Param("token") String token);
  
    @Query(value = "SELECT * FROM account WHERE is_active='false' AND token= :token LIMIT 1", nativeQuery = true)
    Account getAccByToken(@Param("token") String token);
  
    @Query(value = "SELECT * FROM employee WHERE is_delete ='false' AND email= :email", nativeQuery = true)
    Employee getIdByEmail(@Param("email") String email);
    
    @Query(value = "SELECT * FROM employee WHERE id= :id", nativeQuery = true)
    Employee getEmpById(@Param("id") String id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET `password` = :password WHERE username= :username", nativeQuery = true)
    void updatePassword(@Param("username") String username,@Param("password") String password);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET `password` = :password WHERE username= :username", nativeQuery = true)
    void updateAccount(@Param("username") String username,@Param("password") String password);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET `is_active` = :is_active WHERE `token` = :token", nativeQuery = true)
    void updateToken(@Param("token") String token,@Param("is_active") String is_active);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;

import AssetManagement.AssetManagement.entities.EmployeeRole;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ACER
 */
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, String>  {
    
}

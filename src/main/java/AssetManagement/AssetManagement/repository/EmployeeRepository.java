/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;

import AssetManagement.AssetManagement.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{
    @Query(value = "SELECT * FROM employee e WHERE e.is_delete = 'false'", nativeQuery = true)
    List<Employee> getAll();
    @Query(value = "SELECT * FROM employee e WHERE id =?1", nativeQuery = true)
    List<Employee> getEmployeeById(String id);
}

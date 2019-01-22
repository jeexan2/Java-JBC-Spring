package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Geetanjali Oishe on 11/18/2018.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByHrmEmployeeId(Long hrmEmployeeId);
    //    Employee findEmployeeById(Long employeeId);
//    Employee findEmployeeByName(String empNameEnglish);
}

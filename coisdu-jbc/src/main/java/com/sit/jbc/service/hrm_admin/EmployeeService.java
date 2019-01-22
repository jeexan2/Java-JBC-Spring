package com.sit.jbc.service.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTable;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.entity.hrm_admin.Employee;

import java.io.IOException;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/18/2018.
 */
public interface EmployeeService {
    List<EmployeeDataTable> getEmployeeDataTable(String empName, int empId, int empOfc, int empDept,
                                                 int empDesig, int employmentType, int employeeType,
                                                 int start, int length);
    Employee save(EmployeeModel empModel) throws IOException;
    Employee findEmployeeById(Long hrmEmployeeId);
}

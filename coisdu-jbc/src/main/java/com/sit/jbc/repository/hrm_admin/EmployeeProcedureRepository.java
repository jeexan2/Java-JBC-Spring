package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/25/2018.
 */
@Repository
public interface EmployeeProcedureRepository {
    List<EmployeeDataTable> getEmployeeDataTable(String empName, int empId, int empOfc, int empDept,
                                                 int empDesig, int employmentType, int employeeType,
                                                 int start, int length);
}

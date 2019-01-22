package com.sit.jbc.domain.dto.hrm_admin;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/20/2018.
 */
@Getter
@Setter
public class EmployeeDataTable {
    BigDecimal rowId;
    BigDecimal employeeGid;
    String employeeName;
    String office;
    BigDecimal officeId;
    String department;
    BigDecimal departmentId;
    String designation;
    BigDecimal designationId;
    String employementType;
    BigDecimal employementTypeId;
    String employeeType;
    BigDecimal employeeTypeId;
    BigDecimal filteredResultCount;


    public static List<EmployeeDataTable> convertToEmployeeDataTable(List<Object[]> objects, int start){
        List<EmployeeDataTable> ret = new ArrayList<>();
        BigDecimal rowIndex = new BigDecimal(start+1);
        for(Object[] obj : objects){
            EmployeeDataTable employee = new EmployeeDataTable();
            employee.setRowId(rowIndex);
            rowIndex = rowIndex.add(new BigDecimal(1.0));
            employee.setEmployeeGid((BigDecimal) obj[0]);
            employee.setEmployeeName((String) obj[1]);
            employee.setOffice((String)obj[2]);
            employee.setOfficeId((BigDecimal)obj[3]);
            employee.setDesignation((String) obj[4]);
            employee.setDesignationId((BigDecimal)obj[5]);
            employee.setDepartment((String)obj[6]);
            employee.setDepartmentId((BigDecimal)obj[7]);
            employee.setEmployementType((String) obj[8]);
            employee.setEmployementTypeId((BigDecimal)obj[9]);
            employee.setEmployeeType((String) obj[10]);
            employee.setEmployeeTypeId((BigDecimal)obj[11]);
            employee.setFilteredResultCount((BigDecimal)obj[12]);
            ret.add(employee);
        }
        return ret;
    }

}

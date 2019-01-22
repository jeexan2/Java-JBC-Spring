package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/25/2018.
 */
@Repository
public class EmployeeProcedureRepositoryImpl implements EmployeeProcedureRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    public List<EmployeeDataTable> getEmployeeDataTable(String empName, int empId, int empOfc, int empDept,
                                                        int empDesig, int employmentType, int employeeType,
                                                        int start, int length) {
        List<EmployeeDataTable> ret = new ArrayList<EmployeeDataTable>();
        /*StoredProcedureQuery query = em
                .createStoredProcedureQuery("GET_EMPLOYEE_TABLE_LIST")
                .registerStoredProcedureParameter(1, Class.class, ParameterMode.REF_CURSOR)
                .registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
//
//        query.setParameter(1, "EMPLOYEE_ID");

        query.execute();*/

        StoredProcedureQuery query = em.createStoredProcedureQuery("GET_EMPLOYEE_TABLE_LIST")
                .registerStoredProcedureParameter(1 , Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(2, Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(3 , String.class , ParameterMode.IN)
                .registerStoredProcedureParameter(4, Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(5 , Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(6, Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(7 , Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(8, Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(9, Integer.class , ParameterMode.IN)
                .registerStoredProcedureParameter(10, Class.class, ParameterMode.REF_CURSOR);

        query.setParameter(1, start);
        query.setParameter(2, length);
        query.setParameter(3, empName);
        query.setParameter(4, empId);
        query.setParameter(5, empOfc);
        query.setParameter(6, empDept);
        query.setParameter(7, empDesig);
        query.setParameter(8, employmentType);
        query.setParameter(9, employeeType);

        query.execute();
        List<Object[]> objectList = query.getResultList();

        ret = EmployeeDataTable.convertToEmployeeDataTable(objectList, start);
        return ret;
    }

    }

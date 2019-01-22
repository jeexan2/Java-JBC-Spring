package com.sit.jbc.domain.dto.hrm_admin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/26/2018.
 */
@Getter
@Setter
public class EmployeeDataTableAll {
    Integer draw;
    Integer recordsTotal;
    Integer recordsFiltered;
    List<EmployeeDataTable> data;
    String error;
}

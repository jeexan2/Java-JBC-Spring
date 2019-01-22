package com.sit.jbc.service.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.EmployeeImage;

import java.util.List;

public interface EmployeeImageService {
    public List<EmployeeImage> getEmployeeImages(Long hrmEmployeeId);
}
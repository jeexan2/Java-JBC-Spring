package com.sit.jbc.service.hrm_admin.impl;


import com.sit.jbc.domain.entity.hrm_admin.EmployeeImage;
import com.sit.jbc.repository.hrm_admin.EmployeeImageRepository;
import com.sit.jbc.service.hrm_admin.EmployeeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeImageServiceImpl implements EmployeeImageService {
    @Autowired
    EmployeeImageRepository employeeImageRepository;
    @Override
    public List<EmployeeImage> getEmployeeImages(Long hrmEmployeeId) {
        return employeeImageRepository.findByHrmEmployeeIdAndIsActive(hrmEmployeeId, 1L);
    }
}
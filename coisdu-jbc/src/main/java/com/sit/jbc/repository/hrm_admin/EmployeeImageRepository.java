package com.sit.jbc.repository.hrm_admin;

import com.sit.jbc.domain.entity.hrm_admin.EmployeeImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImage, Long> {
    List<EmployeeImage> findByHrmEmployeeIdAndIsActive(Long hrmEmployeeId, Long isActive);
    EmployeeImage findByHrmEmployeeIdAndIsActiveAndImageType(Long hrmEmployeeId, Long isActive, Long imageType);
}
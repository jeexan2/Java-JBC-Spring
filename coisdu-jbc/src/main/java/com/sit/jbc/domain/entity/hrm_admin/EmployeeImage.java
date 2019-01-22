package com.sit.jbc.domain.entity.hrm_admin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name ="HRM_EMPLOYEE_IMAGE")
public class EmployeeImage {
    @Id
    @SequenceGenerator(name="pk_seq_employee_image", sequenceName="HRM_EMPLOYEE_IMAGE_SEQ", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_employee_image")
    @Column(name = "HRM_EMPLOYEE_IMAGE_ID")
    private Long hrmEmployeeImageId;

    @Column(name = "HRM_EMPLOYEE_ID")
    private Long hrmEmployeeId;

    @Column(name = "IMAGE_TYPE")
    private Long imageType;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "IS_ACTIVE")
    private Long isActive;



}
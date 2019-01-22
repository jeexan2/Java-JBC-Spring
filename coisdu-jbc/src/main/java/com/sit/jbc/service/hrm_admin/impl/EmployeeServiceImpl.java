package com.sit.jbc.service.hrm_admin.impl;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTable;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.entity.hrm_admin.Employee;
import com.sit.jbc.domain.entity.hrm_admin.EmployeeImage;
import com.sit.jbc.repository.hrm_admin.EmployeeImageRepository;
import com.sit.jbc.repository.hrm_admin.EmployeeProcedureRepository;
import com.sit.jbc.repository.hrm_admin.EmployeeRepository;
import com.sit.jbc.service.hrm_admin.EmployeeService;
import com.sit.jbc.util.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/18/2018.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeProcedureRepository employeeProcedureRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeImageRepository employeeImageRepository;

    @Autowired
    FileUploader fileUploader;

    @Autowired
    HttpSession httpSession;

    @Override
    public List<EmployeeDataTable> getEmployeeDataTable(String empName, int empId, int empOfc, int empDept,
                                                        int empDesig, int employmentType, int employeeType,
                                                        int start, int length) {
        return employeeProcedureRepository.getEmployeeDataTable(empName, empId, empOfc, empDept, empDesig, employmentType, employeeType, start, length);
    }

    @Override
    public Employee save(EmployeeModel empModel) throws IOException {
        // check for existing employee
        Object empSetupId = httpSession.getAttribute("empSetupId");
        Employee employee = new Employee();
        if(empSetupId != null) {
            employee = employeeRepository.findByHrmEmployeeId((Long)empSetupId);
        }

        employee = convertToEntity(empModel, employee);
        employee = employeeRepository.save(employee);

        //check if the emp photo has been changed and update
        if(empModel.getEmpPhotoStatus() == 1){
            String photoPath = fileUploader.upload(empModel.getEmpPhoto());
            EmployeeImage photoFileInfo = new EmployeeImage();
            if(empSetupId != null) {
                photoFileInfo = employeeImageRepository.findByHrmEmployeeIdAndIsActiveAndImageType(employee.getHrmEmployeeId(), 1L, 1L);
            }
            else {
                photoFileInfo.setHrmEmployeeId(employee.getHrmEmployeeId());
                photoFileInfo.setIsActive(1L);
                photoFileInfo.setImageType(1L);
            }
            photoFileInfo.setImagePath(photoPath);
            employeeImageRepository.save(photoFileInfo);
        }
        //check if the emp sig has been changed and update
        if(empModel.getEmpSigStatus() == 1){
            String sigPath = fileUploader.upload(empModel.getEmpSig());
            EmployeeImage sigFileInfo = new EmployeeImage();
            if(empSetupId != null) {
                sigFileInfo = employeeImageRepository.findByHrmEmployeeIdAndIsActiveAndImageType(employee.getHrmEmployeeId(), 1L, 2L);
            }
            else {
                sigFileInfo.setHrmEmployeeId(employee.getHrmEmployeeId());
                sigFileInfo.setIsActive(1L);
                sigFileInfo.setImageType(2L);
            }
            sigFileInfo.setImagePath(sigPath);
            employeeImageRepository.save(sigFileInfo);
        }

        return employee;
    }

    @Override
    public Employee findEmployeeById(Long hrmEmployeeId) {
        return employeeRepository.findByHrmEmployeeId(hrmEmployeeId);
    }

    private Employee convertToEntity(EmployeeModel empModel, Employee employee) {
//        Employee employee = new Employee();

//        employee.setEmpGid(empModel.getEmpGid());
        employee.setEmpId(empModel.getEmpId());

        employee.setEmpFileNo(empModel.getEmpFileNo());
        employee.setSalut(empModel.getSalut());
        employee.setEmpNmEng(empModel.getEmpNmEng());
        employee.setEmpNmBng(empModel.getEmpNmBng());
        employee.setBirthDt(empModel.getBirthDt());
        employee.setJoinDt(empModel.getJoinDt());
        employee.setConfirmDt(empModel.getConfirmDt());
        employee.setLprDt(empModel.getLprDt());
        employee.setRetireDt(empModel.getRetireDt());


//        employee.setPayLevel(empModel.getPayLevel());
//        employee.setCurrBasic(empModel.getCurrBasic());
//        employee.setLastIncrDt(empModel.getLastIncrDt());
//        employee.setLastPromDt(empModel.getLastPromDt());
//        employee.setPenPayStat(empModel.getPenPayStat());
//        employee.setPreviousBasic(empModel.getPreviousBasic());
//
//        employee.setPfcDedStat(empModel.getPfcDedStat());
//        employee.setEmpGlacNo(empModel.getEmpGlacNo());
//        employee.setTargetBasis(empModel.getTargetBasis());
//        employee.setTargetAmount(empModel.getTargetAmount());

//        employee.setEffectDt(empModel.getEffectDt());
//        employee.setTargetTerm(empModel.getTargetTerm());
//        employee.setSalPayStat(empModel.getSalPayStat());
//        employee.setBankAccountNo(empModel.getBankAccountNo());
//
//        employee.setBankAccountStatus(empModel.getBankAccountStatus());
//        employee.setMemType(empModel.getMemType());
//        employee.setEffectDtOnSalary(empModel.getEffectDtOnSalary());

//        employee.setFreedomFighterSt(empModel.getFreedomFighterSt());
//        employee.setDisabilityEmpSt(empModel.getDisabilityEmpSt());
//        employee.setDisabilityChildSt(empModel.getDisabilityChildSt());

        employee.setDesignationId(empModel.getDesignationId());
        employee.setOfficeId(empModel.getOfficeId());
        employee.setGradeId(empModel.getGradeId());
        employee.setReligionId(empModel.getReligionId());
        employee.setMunilocId(empModel.getMunilocId());
        employee.setGenderId(empModel.getGenderId());
        employee.setEmploymentTypeId(empModel.getEmploymentTypeId());
        employee.setEmployeeTypeId(empModel.getEmployeeTypeId());
        employee.setDivdeptId(empModel.getDivdeptId());
        employee.setMaritalStatusId(empModel.getMaritalStatusId());
        employee.setActStatusId(empModel.getActStatusId());
        employee.setEmploymentStatusId(empModel.getEmploymentStatusId());
        employee.setServiceStatusId(empModel.getServiceStatusId());


//        employee.setActivityCd(empModel.getActivityCd());
//        employee.setServiceCd(empModel.getServiceCd());
//        employee.setEmployementStCd(empModel.getEmployementStCd());
//        employee.setPaysId(empModel.getPaysId());
//        employee.setMaritalStatCd(empModel.getMaritalStatCd());
//        employee.setBankBrCd(empModel.getBankBrCd());

        return employee;
    }

}

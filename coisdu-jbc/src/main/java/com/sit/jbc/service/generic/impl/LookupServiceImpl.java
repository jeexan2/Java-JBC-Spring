package com.sit.jbc.service.generic.impl;

import com.sit.jbc.domain.dto.generic.LookUpDataTable;
import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.repository.generic.LookupRepository;
import com.sit.jbc.repository.security.LookupProcedureRepository;
import com.sit.jbc.service.generic.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {
    @Autowired
    LookupRepository lookupRepository;
    @Autowired
    LookupProcedureRepository lookupProcedureRepository;

    @Override
    public List<Lookup> findByType(String type){
        return lookupRepository.findByType(type);
    }

    @Override
    public List<Lookup> findOfficeByOfficeCategoryForDropdown(){
        List<Lookup> officeCategory = new ArrayList<Lookup>();
        Lookup select = new Lookup();
        select.setDisplayName("Select Category");
        select.setValue("-1");
        officeCategory.add(select);
        officeCategory.addAll(lookupRepository.findByType("OFFICE_CATEGORY"));
        return officeCategory;
    }

    @Override
    public List<Lookup> findMunicipalityInfo(){
        List<Lookup> lookups = new ArrayList<Lookup>();
        Lookup lookup = new Lookup();
        lookup.setValue("-1");
        lookup.setDisplayName("Select Municipality");
        lookups.add(lookup);
        lookups.addAll(lookupRepository.findByType("MUNI LOC"));
        return lookups;
    }

    @Override
    public List<Lookup> findAll() {
        return lookupRepository.findByIsDeleted(false);
    }

    @Override
    public List<String> getTypeList() {
       /** List<String> ret = new ArrayList<String>();
        ret.add("Select Type");
        ret.addAll(lookupProcedureRepository.getLookupTypeList());
        return ret;**/
       return lookupProcedureRepository.getLookupTypeList();
    }

    @Override
    public List<LookUpDataTable> getLookupDetails() {
         List<Lookup> lookUpList = new ArrayList<Lookup>();
         lookUpList = lookupRepository.findByIsDeleted(false);
        return LookUpDataTable.convertToLookUpPojo(lookUpList);
    }

    @Override
    public Lookup save(Lookup lookup) {
        if (lookup.getLookupId() == -1) {
            lookup.setCreatedOn(new Date());
            lookup.setCreatedBy(1);
            lookup.setIsDeleted(false);
            lookup.setIsMigrated(false);

        } else {
            Lookup tempLookup = lookupRepository.findByLookupId(lookup.getLookupId());
            lookup.setIsUpdated(1);
            lookup.setUpdatedOn(new Date());
            lookup.setCreatedBy(tempLookup.getCreatedBy());
            lookup.setCreatedOn(tempLookup.getCreatedOn());
            lookup.setIsMigrated(tempLookup.getIsMigrated());
            lookup.setIsDeleted(tempLookup.getIsDeleted());

        }
        return lookupRepository.save(lookup);
    }

    @Override
    public void delete(Lookup lookup) {
        lookupRepository.delete(lookup);
    }

    @Override
    public Lookup findByGenLookupId(Long id) {
        return lookupRepository.findByLookupId(id);
    }

    @Override
    public void safeDelete(long id) {
        Lookup lookup = lookupRepository.findByLookupId(id);
        lookup.setIsDeleted(true);
        lookup.setDeletedOn(new Date());
        lookupRepository.save(lookup);
    }
    @Override
    public List<Lookup> findOfficeByOfficeCategory(){
        return lookupRepository.findByType("OFFICE_CATEGORY");
    }
    @Override
    public List<Lookup> findEmploymentType(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYMENT_TYPE", 1L);
    }
    @Override
    public List<Lookup> findEmployeeType(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYEE_TYPE", 1L);
    }
    @Override
    public List<Lookup> findGender(){
        return lookupRepository.findByTypeAndIsActive("GENDER", 1L);
    }
    @Override
    public List<Lookup> findMaritalStatus(){
        return lookupRepository.findByTypeAndIsActive("MARITAL_STATUS", 1L);
    }
    @Override
    public List<Lookup> findReligion(){
        return lookupRepository.findByTypeAndIsActive("RELIGION", 1L);
    }
    @Override
    public List<Lookup> findDivDept(){
        return lookupRepository.findByTypeAndIsActive("DIVDEPT", 1L);
    }
    @Override
    public List<Lookup> findServiceStatus(){
        return lookupRepository.findByTypeAndIsActive("SERVICE_STATUS", 1L);
    }
    @Override
    public List<Lookup> findPfType(){
        return lookupRepository.findByTypeAndIsActive("CPF_PF_TYPE", 1L);
    }
    @Override
    public List<Lookup> findBanks(){
        return lookupRepository.findByTypeAndIsActive("BANK", 1L);
    }
    @Override
    public List<Lookup> findBranch(Long bankId){
        return lookupRepository.findByTypeAndIsActiveAndRefId("BANK_BRANCH", 1L, bankId);
    }
    @Override
    public List<Lookup> findEmployeeStatus(){
        return lookupRepository.findByTypeAndIsActive("EMPLOYMENT_STATUS", 1L);
    }
    @Override
    public List<Lookup> findActivityStatus(){
        return lookupRepository.findByTypeAndIsActive("ACTIVITY_STATUS", 1L);
    }

}
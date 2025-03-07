package com.sit.jbc.service.generic;

import com.sit.jbc.domain.dto.generic.LookUpDataTable;
import com.sit.jbc.domain.entity.generic.Lookup;

import java.util.List;

/**
 * Created by User on 16-Oct-18.
 */

public interface LookupService {
    List<Lookup> findByType(String type);
    List<Lookup> findOfficeByOfficeCategoryForDropdown();
    List<Lookup> findMunicipalityInfo();
    List<Lookup> findAll();
    List<String> getTypeList();
    List<LookUpDataTable> getLookupDetails();
    Lookup save(Lookup lookup);
    void delete(Lookup lookup);
    Lookup findByGenLookupId(Long id);
    void safeDelete(long id);
    List<Lookup> findOfficeByOfficeCategory();
    List<Lookup> findEmploymentType();
    List<Lookup> findEmployeeType();
    List<Lookup> findGender();
    List<Lookup> findMaritalStatus();
    List<Lookup> findReligion();
    List<Lookup> findDivDept();
    List<Lookup> findServiceStatus();
    List<Lookup> findPfType();
    List<Lookup> findBanks();
    List<Lookup> findBranch(Long bankId);
    List<Lookup> findEmployeeStatus();
    List<Lookup> findActivityStatus();

}

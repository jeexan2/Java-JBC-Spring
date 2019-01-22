package com.sit.jbc.controller.hrm_admin;

import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTable;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeDataTableAll;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeModel;
import com.sit.jbc.domain.dto.hrm_admin.EmployeeUpdate;
import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.entity.generic.Lookup;
import com.sit.jbc.domain.entity.hrm_admin.Employee;
import com.sit.jbc.domain.entity.hrm_admin.EmployeeImage;
import com.sit.jbc.service.generic.LookupService;
import com.sit.jbc.service.hrm_admin.DesignationService;
import com.sit.jbc.service.hrm_admin.EmployeeImageService;
import com.sit.jbc.service.hrm_admin.EmployeeService;
import com.sit.jbc.service.hrm_admin.GradeService;
import com.sit.jbc.service.security.OfficeService;
import com.sit.jbc.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geetanjali Oishe on 11/18/2018.
 */
@Controller
@RequestMapping(value = "/hrm_admin")
public class EmployeeController {
    @Autowired
    HttpSession httpSession;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OfficeService officeService;

    @Autowired
    LookupService lookupService;

    @Autowired
    DesignationService designationService;

    @Autowired
    GradeService gradeService;

    @Autowired
    EmployeeImageService employeeImageService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeDtable(Model model) {
        try {
            AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
            model.addAttribute("permissionCheck", accessPermission);
        } catch (Exception e) {
        }
        model.addAttribute("officeList", officeService.findAll());
        model.addAttribute("departmentList", lookupService.findByType("DIVDEPT"));
        model.addAttribute("designationList", designationService.findAll().stream().filter(s -> s.getIsActive() == 1).collect(Collectors.toList()));
        model.addAttribute("employeeTypeList", lookupService.findByType("EMPLOYEE_TYPE"));
        model.addAttribute("employmentTypeList", lookupService.findByType("EMPLOYMENT_TYPE"));
        //        model.addAttribute("districtTableData",employeeService.getEmployeeDataTable());
        return "hrm_admin/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String employeeDtable(@Validated @ModelAttribute("employee") EmployeeUpdate employeeUpdate, BindingResult result, Model model) {


        return "hrm_admin/employee";
    }

    @RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
    @ResponseBody
    public EmployeeDataTableAll getAllEmployees(HttpServletRequest request,
                                                HttpServletResponse response) {
        int draw = Integer.parseInt(request.getParameter("draw"));
        int start = Integer.parseInt(request.getParameter("start"));
        int length = Integer.parseInt(request.getParameter("length"));
        String empId = request.getParameter("empId");
        String empOfc = request.getParameter("empOfc");
        String empDept = request.getParameter("empDept");
        String empDesig = request.getParameter("empDesig");
        String employmentType = request.getParameter("employmentType");
        String employeeType = request.getParameter("employeeType");
        String empName = (request.getParameter("empName").isEmpty() ? "" : "%" + request.getParameter("empName") + "%");

        int empId2 = (empId.isEmpty()) ? -1 : Integer.parseInt(empId);
        int empOfc2 = Integer.parseInt(empOfc);
        int empDept2 = Integer.parseInt(empDept);
        int empDesig2 = Integer.parseInt(empDesig);
        int employmentType2 = Integer.parseInt(employmentType);
        int employeeType2 = Integer.parseInt(employeeType);


        List<EmployeeDataTable> employeeDataTable = employeeService.getEmployeeDataTable(empName, empId2, empOfc2, empDept2, empDesig2, employmentType2, employeeType2, start, length);

        EmployeeDataTableAll employeeDataTableAll = new EmployeeDataTableAll();
        employeeDataTableAll.setData(employeeDataTable);
        employeeDataTableAll.setDraw(draw);
        employeeDataTableAll.setError("");
        employeeDataTableAll.setRecordsTotal(39477);
        employeeDataTableAll.setRecordsFiltered(
                (employeeDataTable.size() > 0) ?
                        employeeDataTable.get(0).getFilteredResultCount().intValue() : 0);  //total match found
        return employeeDataTableAll;
//        return null;
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteEmployee(@RequestParam("empID") Long elementId) {
        return null;
    }

    @RequestMapping(value = "/employee_general_setup", method = RequestMethod.POST)
    public String setupGeneralEmployee(@Validated @ModelAttribute("employeemodel")EmployeeModel employeeModel,
                                       BindingResult result, Model model,
                                       RedirectAttributes redirectAttributes) throws IOException {

        Employee employee = new Employee();

        try{
            employee = employeeService.save(employeeModel);
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("status", "error");
            throw e;
        }
        httpSession.setAttribute("empSetupId", employee.getHrmEmployeeId());
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/hrm_admin/employee_setup";
    }

    @RequestMapping(value = "/employee_setup", method = RequestMethod.GET)
    public String setupEmployee(Model model) {
        Object empSetupId = httpSession.getAttribute("empSetupId");
        Employee employee = new Employee();
        List<EmployeeImage> employeeImages = new ArrayList<>();
        EmployeeImage empPhoto = new EmployeeImage();
        EmployeeImage sigPhoto = new EmployeeImage();
        int empPhotoStatus = 0; // 0 = validate, 1 = upload, 2 = dont do anything
        int sigPhotoStatus = 0;

        if(empSetupId != null) {
            System.out.println("empSetupId: " + empSetupId );
            employee = employeeService.findEmployeeById((Long)empSetupId);
        }

//        employee = employeeService.findEmployeeById(39507L);
//        httpSession.setAttribute("empSetupId", employee.getHrmEmployeeId());

        if(employee.getHrmEmployeeId() != null){
            employeeImages = employeeImageService.getEmployeeImages(employee.getHrmEmployeeId());
            if(employeeImages.size() > 0){
                empPhoto = employeeImages
                        .stream()
                        .filter(s -> s.getImageType() == 1)
                        .findFirst()
                        .orElse(null);
                sigPhoto = employeeImages
                        .stream()
                        .filter(s -> s.getImageType() == 2)
                        .findFirst()
                        .orElse(null);
                empPhotoStatus = empPhoto == null ? 0 : 2;
                sigPhotoStatus = sigPhoto == null ? 0 : 2;
            }
        }

        AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
        model.addAttribute("permissionCheck", accessPermission);
        model.addAttribute("employmentTypeList", lookupService.findEmploymentType());
        model.addAttribute("employeeType", lookupService.findEmployeeType());
        model.addAttribute("employeeGenderList", lookupService.findGender());
        model.addAttribute("employeeReligionList", lookupService.findReligion());
        model.addAttribute("employeeMarList", lookupService.findMaritalStatus());
        model.addAttribute("employeeDesigList", designationService.findAllActive(1L));
        model.addAttribute("employeeGradeList", gradeService.findAllActive(1L));
        model.addAttribute("employeeOfficeList", officeService.findAllActive(1L));
        model.addAttribute("employeeDivDeptList", lookupService.findDivDept());

        model.addAttribute("employeeServiceStatusList", lookupService.findServiceStatus());
        model.addAttribute("employeeSalaryPaymentServiceStatusList", null);

        model.addAttribute("pfDeductionStatusList", null);
        model.addAttribute("pfTypeList", lookupService.findPfType());
        model.addAttribute("pensionPaymentStatusList", null);
        model.addAttribute("bankList", lookupService.findBanks());
        model.addAttribute("employeeStatusList", lookupService.findEmployeeStatus());
        model.addAttribute("employeeActStatusList", lookupService.findActivityStatus());

        model.addAttribute("empImagePhoto", empPhoto);
        model.addAttribute("empSigPhoto", sigPhoto);

        model.addAttribute("empPhotoStatus", empPhotoStatus);
        model.addAttribute("empSigStatus", sigPhotoStatus);

        model.addAttribute("employee", employee);

        return "hrm_admin/employee_setup";
    }

    @RequestMapping(value = "/get_branch_details", method = RequestMethod.POST)
    @ResponseBody
    public List<Lookup> getBranchDetails(Long bankId) {
        return lookupService.findBranch(bankId);
    }





}

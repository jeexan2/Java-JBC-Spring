package com.sit.jbc.controller.security;

import com.sit.jbc.domain.dto.security.AccessPermission;
import com.sit.jbc.domain.dto.security.TreeElement;
import com.sit.jbc.domain.dto.security.UserRolePermission;
import com.sit.jbc.domain.entity.security.Module;
import com.sit.jbc.domain.entity.security.OptionSetupView;
import com.sit.jbc.domain.entity.security.Role;
import com.sit.jbc.domain.entity.security.RoleOption;
import com.sit.jbc.service.security.*;
import com.sit.jbc.service.security.impl.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Geetanjali Oishe on 10/31/2018.
 */

@Controller
@RequestMapping(value = "/security")
public class UserRolePermissionController {
    @Autowired
    ModuleService moduleService;

    @Autowired
    RoleOptionServie roleOptionServie;

    @Autowired
    OptionSetupViewService optionSetupViewService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRolePermissionService userRolePermissionService;

    @Autowired
    HttpSession httpSession;


    @RequestMapping(value = "/user_role_permission", method = RequestMethod.GET)
    public String userRolePermission(Model model) {
//        model.addAttribute("test", "test");
        return "security/user_role_permission";
    }

    @RequestMapping(value = "/user_role_permission", method = RequestMethod.POST)
    public String userRolePermission(@Validated @ModelAttribute("role-permission") UserRolePermission userRolePermission, BindingResult result, Model model) {
        return userRolePermissionService.userRolePermission(userRolePermission);
    }

    @RequestMapping(value = "/getModuleList2", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Module> getModuleList() {
        return userRolePermissionService.getModuleList();
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Role> getRoleList() {
        return userRolePermissionService.getRoleList();
    }

    @RequestMapping(value = "/getModuleWiseTree", method = RequestMethod.GET)
    public
    @ResponseBody
    String getModuleWiseTree(@RequestParam("moduleId") Long moduleId, @RequestParam("roleId") Long roleId) {
        return userRolePermissionService.getModuleWiseTree(moduleId, roleId);
    }


    @RequestMapping(value = "/getCurrentPermission", method = RequestMethod.GET)
    public
    @ResponseBody
    List<RoleOption> getCurrentPermission() {
        return userRolePermissionService.getCurrentPermission();
    }
/**    @RequestMapping(value = "/getUserPermission", method = RequestMethod.GET)
public @ResponseBody
AccessPermission getPermission() {

try {
AccessPermission accessPermission = (AccessPermission) httpSession.getAttribute("userPermission");
return accessPermission;
}
catch (Exception e){
return new AccessPermission((long)0, (long)0, (long)0);
}
} **/

}

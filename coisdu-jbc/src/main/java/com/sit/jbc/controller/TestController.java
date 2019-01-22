package com.sit.jbc.controller;

/**
 * Created by Geetanjali Oishe on 11/19/2018.
 */
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sit.jbc.domain.entity.security.User;
import com.sit.jbc.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author pavan.solapure
 *
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserRepository userRepo;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;




    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String home() {
        return "Test/Users";
    }

    @RequestMapping(path="/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public List<User> getAllEmployees(HttpServletRequest request,
                                      HttpServletResponse response){
        return userRepo.findAll();
    }


    @RequestMapping(value="/users/mysql", method=RequestMethod.GET)
    public String listUsers(Model model) {
        return "users_mysql";
    }


    @RequestMapping(value="/users/oracle", method=RequestMethod.GET)
    public String listUsersOracle(Model model) {
        return "users";
    }

}
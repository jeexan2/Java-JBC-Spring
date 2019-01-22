package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;


/**
* Created By User on 26-12-2018
**/


public interface TestRepository extends JpaRepository<Test, Long> {
    
}

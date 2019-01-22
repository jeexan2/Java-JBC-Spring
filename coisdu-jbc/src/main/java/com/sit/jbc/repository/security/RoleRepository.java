package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MAMUN on 11-Sep-18.
 */

public interface RoleRepository  extends JpaRepository<Role,Long> {
    
    Role findByRoleId(Long id);
}
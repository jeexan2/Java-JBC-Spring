package com.sit.jbc.repository.security;

import com.sit.jbc.domain.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by User on 22-Oct-18.
 */

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserId(long userId);
    User findByUsername(String userName);

    @Query(
            value = "SELECT SEC_USER_ID FROM SEC_USER ORDER BY SEC_USER_ID ASC OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY",
            nativeQuery = true)
    Collection<User> getDTable();
}

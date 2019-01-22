package com.sit.jbc.repository;

import com.sit.jbc.domain.entity.security.User;

import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/20/2018.
 */
public interface UserQueryRepo {
    List<User> getDTable();
}

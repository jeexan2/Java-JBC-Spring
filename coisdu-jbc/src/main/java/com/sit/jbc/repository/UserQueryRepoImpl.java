package com.sit.jbc.repository;

import com.sit.jbc.domain.entity.security.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 11/20/2018.
 */
@Repository
public class UserQueryRepoImpl implements UserQueryRepo {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getDTable() {
        List<User> ret = new ArrayList<User>();
        List<String> ret1 = new ArrayList<>();

        Query query = em.createNativeQuery(  "SELECT * from SEC_USER");
        ret =  query.getResultList();

//        Query query1= em.createNativeQuery("SELECT SEC_USER_ID FROM SEC_USER ORDER BY SEC_USER_ID ASC OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY");
        Query query1 = em.createNativeQuery("SELECT DISPLAY_NAME, SHORT_NAME FROM GEN_LOOKUP");
        ret1 = query1.getResultList();

        return ret;
    }


}

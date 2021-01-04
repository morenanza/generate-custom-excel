package com.morenanza.excel.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UsersDaoCustomImpl implements UsersDaoCustom {

    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Override
    public List<Object[]> recoverData(String queryCustom) {

        Query sql = em.createNativeQuery(queryCustom);
        return sql.getResultList();

    }
}

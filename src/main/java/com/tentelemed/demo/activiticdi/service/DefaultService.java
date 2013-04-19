package com.tentelemed.demo.activiticdi.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 19/04/13
 * Time: 16:24
 */
public abstract class DefaultService {
    @PersistenceContext(name = "Demo_Unit")
    protected EntityManager em;

    protected void persist(Object entity) {
        em.persist(entity);
    }

    protected <T extends Object> T unique(TypedQuery<T> query) {
        List<T> res = query.getResultList();
        if (res.isEmpty()) {
            return null;
        } else {
            return res.get(0);
        }
    }

}

package com.tentelemed.demo.activiticdi.service;

import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

    @Inject
    Logger log;

    @PersistenceContext(name = "Demo_Unit")
    protected EntityManager em;

    protected BeanManager getBeanManager() {
        try {
            InitialContext initialContext = new InitialContext();
            return (BeanManager) initialContext.lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            log.error("Couldn't get BeanManager through JNDI");
            return null;
        }
    }


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

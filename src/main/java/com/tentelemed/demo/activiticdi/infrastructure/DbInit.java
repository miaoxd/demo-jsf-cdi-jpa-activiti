package com.tentelemed.demo.activiticdi.infrastructure;

import com.tentelemed.demo.activiticdi.bo.User;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 19/04/13
 * Time: 15:11
 */
@Singleton
@Startup
@DependsOn("StartDB")
public class DbInit {

    @PersistenceContext(name = "Demo_Unit")
    EntityManager em;

    @PostConstruct
    public void initDb() {
        Long res = em.createQuery("select count(u) from User u", Long.class).getResultList().get(0);
        if (res == 0) {
            for (int i=0; i<10; i++) {
                User user = new User("login"+i, "password"+i, "Paul"+i, "Durand"+i);
                em.persist(user);
            }
        }
    }
}

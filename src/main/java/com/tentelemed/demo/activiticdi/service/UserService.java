package com.tentelemed.demo.activiticdi.service;

import com.tentelemed.demo.activiticdi.bo.User;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 19/04/13
 * Time: 16:23
 */
@Stateless
@Named
public class UserService extends DefaultService {

    public User find(String login, String password) {
        return unique(
                em.createQuery("select u from User u where u.login=:login and u.password=:password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
        );

    }

}
